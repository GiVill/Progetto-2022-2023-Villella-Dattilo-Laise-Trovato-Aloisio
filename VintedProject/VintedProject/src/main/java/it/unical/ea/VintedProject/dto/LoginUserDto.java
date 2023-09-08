package it.unical.ea.VintedProject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class LoginUserDto {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
