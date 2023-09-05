package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.dto.enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewUserDto {

    @NotNull(message = "password.not.empty")
    private String password;

    @NotNull(message = "nickname.not.empty")
    private String nickname;

    @NotNull(message = "password.not.empty") //TODO cambiare
    private String firstname;

    @Email(message = "email.not.valid")
    private String email;

    private Role role;
    private String lastname;
    private String addressStreet;
    private Integer addressNumber;
    private String addressCity;
    private Integer addressCap;
}
