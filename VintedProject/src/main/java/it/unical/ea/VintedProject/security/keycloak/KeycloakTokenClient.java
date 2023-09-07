package it.unical.ea.VintedProject.security.keycloak;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
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
    private final LoggedUserMethod loggedUserMethod;
    private static final String TOKEN_ENDPOINT = "http://localhost:8090/realms/vinted2_0/protocol/openid-connect/token";

    public Keycloak getAdminKeycloakUser() {
        return KeycloakBuilder.builder().serverUrl("http://localhost:8090/")
                .grantType("password").realm("vinted2_0")
                .clientId("vinted-client")
                .username("globaladmin")
                .password("admin")
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }

    public TokenDto getRefreshToken(String refreshToken){

        System.out.println(refreshToken);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("client_id", "vinted-client");
        body.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<TokenResponse> responseEntity = restTemplate.exchange(
                TOKEN_ENDPOINT,
                HttpMethod.POST,
                requestEntity,
                TokenResponse.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            TokenResponse tokenResponse = responseEntity.getBody();
            TokenDto tokenDto = new TokenDto(tokenResponse.getAccess_token(), tokenResponse.getRefresh_token(), tokenResponse.getToken_type(),tokenResponse.getUserDto());
            return tokenDto;
        } else {
            System.out.println("error while retrieving the token from keycloak!");
            //TODO:gestire errore
            throw new RuntimeException(messageLang.getMessage("keycloak.token.error"));
        }
    }


    public RealmResource getRealm() {
        return getAdminKeycloakUser().realm("vinted2_0");
    }

    public TokenDto userRegister(NewUserDto newUserDto){
        //TODO: registrazione utente su keycloak
        if(addUserOnKeyCloak(newUserDto)){
            return getToken(newUserDto.getNickname(),newUserDto.getPassword());
        }
        return null; //throw new RuntimeException(messageLang.getMessage("keycloak.token.error")); ?????
    }

    public TokenDto getToken(String username, String password) {
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
            TokenDto tokenDto = new TokenDto(tokenResponse.getAccess_token(), tokenResponse.getRefresh_token(), tokenResponse.getToken_type(),tokenResponse.getUserDto());
            return tokenDto;
        } else {
            System.out.println("error while retrieving the token from keycloak!");
            //TODO:gestire errore
            throw new RuntimeException(messageLang.getMessage("keycloak.token.error"));
        }
    }

    private UserRepresentation newUserDtoConverter(NewUserDto newUserDto){

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(newUserDto.getNickname());
        userRepresentation.setEmail(newUserDto.getEmail());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);

        return  userRepresentation;
    }

    public boolean updateUserPassword(String newPassword){

        String keycloakId = loggedUserMethod.getLoggedUserIdKeycloak();

        System.out.println(keycloakId);


        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(newPassword);

        RealmResource realmResource = getAdminKeycloakUser().realm("vinted2_0");
        UserResource userResource = realmResource.users().get(keycloakId);

        userResource.resetPassword(passwordCred);

        return true;
    }

    public boolean addUserOnKeyCloak(NewUserDto newUserDto){
        try {


            UserRepresentation userRepresentation = newUserDtoConverter(newUserDto);

            RealmResource realmResource = getAdminKeycloakUser().realm("vinted2_0");
            UsersResource usersResource = realmResource.users();

            Response response = usersResource.create(userRepresentation);

            System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());

            System.out.println(response.getLocation());
            String userId = CreatedResponseUtil.getCreatedId(response);

            System.out.printf("User created with userId: %s%n", userId);

            // Define password credential
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(newUserDto.getPassword());
            //System.out.println(passwordCred);
            //System.out.println(userRepresentation);

            UserResource userResource = usersResource.get(userId);

            // Set password credential
            userResource.resetPassword(passwordCred);

            // Get realm role "app_user" (requires view-realm role)
            RoleRepresentation appUserRole = realmResource.roles()//
                    .get("app_admin").toRepresentation();


            // Assign realm role tester to user
            userResource.roles().realmLevel() //
                    .add(Arrays.asList(appUserRole));

            return true;
        }catch (Exception e){
            return false;
        }
    }
}
