package it.unical.ea.VintedProject.dto;

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

    @NotNull(message = "nickname.notempty")
    private String password;

    @NotNull(message = "nickname.notempty")
    private String nickName;

    @Email(message = "email.valid")
    private String email;

}
