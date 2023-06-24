package it.unical.ea.VintedProject.security.keycloak;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.dto.NewUserDto;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class KeycloakTokenClient {


    private final MessageLang messageLang;

    private static final String ADMIN_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ1TkJYcVBTLUwtYlhxTUJPWEh5Nk1pZFBjQWdIU3FQeGtpQlpDa0hldWtVIn0.eyJleHAiOjE2ODczOTk5MjYsImlhdCI6MTY4NzM2MzkyNiwianRpIjoiZTMwZGI4ODEtY2EzZS00ZmNkLTllZTgtZTQ4NmY2OTI2MGI0IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDkwL3JlYWxtcy92aW50ZWQyXzAiLCJhdWQiOlsicmVhbG0tbWFuYWdlbWVudCIsImFjY291bnQiXSwic3ViIjoiMTA5MGVlNjYtYTMxOC00MGY3LTgzYmItMzY4ODRkNzY4ZWI2IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidmludGVkLWNsaWVudCIsInNlc3Npb25fc3RhdGUiOiI2ZWU3MTZhMS00OTk1LTRkYzYtOTcwNy1mMmYxYTE3MmQ3MDkiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA5MSJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImRlZmF1bHQtcm9sZXMtdmludGVkMl8wIiwiYXBwX2FkbWluIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsicmVhbG0tbWFuYWdlbWVudCI6eyJyb2xlcyI6WyJtYW5hZ2UtdXNlcnMiLCJ2aWV3LXVzZXJzIiwicXVlcnktZ3JvdXBzIiwicXVlcnktdXNlcnMiXX0sInZpbnRlZC1jbGllbnQiOnsicm9sZXMiOlsiYWRtaW4iXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInNpZCI6IjZlZTcxNmExLTQ5OTUtNGRjNi05NzA3LWYyZjFhMTcyZDcwOSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJnbG9iYWxhZG1pbiIsImdpdmVuX25hbWUiOiIiLCJmYW1pbHlfbmFtZSI6IiIsImVtYWlsIjoiYWRtaW5AbWFpbC5jb20ifQ.knrvBEz2R7WBMQs8O6fhgbfzb81oOVsfO6kEwTuEpmztYypuQ3ykq4J-ENDiCZsZEtii2V0fTtZsyYPORnaemgYfgjhJFP0ex5g5bcOWnFcYjcNTgT9-3WkwIJc7aCDWdfAAWWxf7uq5EqwM2_8Z7f5aMF-tn_jCP680IhWFIesdsQJijkKGfuZG1xxgAnv2WSfdjcuUs3lLwq4wmoSdtzibvWN-2cVmm-2vqceKlTlJvJL78pP_5fLCpNq2CBGZs1DWjjOELgWf-oNRZwwrMTJoSlNWwE7RRBccDCnmmvPpUsGEbcfHCzl7Ya_ByONi3sPlxt_6RuR2DTCDxtxeBA";
    private static final String TOKEN_ENDPOINT = "http://localhost:8090/realms/vinted2_0/protocol/openid-connect/token";

    private static final String REGISTER_ENDPOINT = "http://localhost:8090/admin/realms/vinted2_0/users";

    public Keycloak getAdminKeycloakUser() {
        return KeycloakBuilder.builder().serverUrl("http://localhost:8090/")
                .grantType("password").realm("vinted2_0")
                .clientId("vinted-client")
                .username("globaladmin")
                .password("admin")
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }
    public RealmResource getRealm() {
        return getAdminKeycloakUser().realm("vinted2_0");
    }

    public String userRegister(NewUserDto newUserDto){

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(newUserDto.getNickName());
        userRepresentation.setEmail(newUserDto.getEmail());
        userRepresentation.setEmailVerified(true);

        RealmResource realmResource = getAdminKeycloakUser().realm("vinted2_0");
        UsersResource usersRessource = realmResource.users();

        userRepresentation.setEnabled(true);

        Response response = usersRessource.create(userRepresentation);
        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
        System.out.println(response.getLocation());
        String userId = CreatedResponseUtil.getCreatedId(response);

        System.out.printf("User created with userId: %s%n", userId);

        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(newUserDto.getPassword());

        UserResource userResource = usersRessource.get(userId);

        // Set password credential
        userResource.resetPassword(passwordCred);

        //        // Get realm role "tester" (requires view-realm role)
        RoleRepresentation testerRealmRole = realmResource.roles()//
                .get("app_user").toRepresentation();

        return null;
    }

    /*
    public String userRegister(NewUserDto newUserDto){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.printf(newUserDto.toString());
        HttpEntity<String> requestEntity = new HttpEntity<>(newUserDto.toString(), headers);

        ResponseEntity<TokenResponse> responseEntity = restTemplate.exchange(
                TOKEN_ENDPOINT,
                HttpMethod.POST,
                requestEntity,
                TokenResponse.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String token = getToken(newUserDto.getNickName(), newUserDto.getPassword());
            return token;
        } else {
            System.out.println("error while retrieving the token from keycloak!");
            //todo:gestire errore
            throw new RuntimeException("ciao");
        }
    }
     */

    public String getToken(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", "vinted-client");
        body.add("username", username);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<TokenResponse> responseEntity = restTemplate.exchange(
                TOKEN_ENDPOINT,
                HttpMethod.POST,
                requestEntity,
                TokenResponse.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            TokenResponse tokenResponse = responseEntity.getBody();
            return tokenResponse.getAccess_token();
        } else {
            System.out.println("error while retrieving the token from keycloak!");
            //todo:gestire errore
            throw new RuntimeException("ciao");
        }
    }
}