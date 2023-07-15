package it.unical.ea.VintedProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserDto {

    private Long id;
    @NotNull(message = "firstname.not.empty")
    private String firstName;
    @NotNull(message = "lastname.not.empty")
    private String lastName;
    private String imageName;
    private String nickname;
    @Email(message = "email.not.valid")
    private String email;
    private String addressStreet;
    private Integer addressNumber;
    private String addressCity;
    private Integer addressCap;
    private String addressState;
    private String addressRegion;

}
