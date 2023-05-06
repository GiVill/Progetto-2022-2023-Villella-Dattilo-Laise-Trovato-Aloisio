package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.dto.enumerated.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class UserDto {

    /*
    Utente:
        -ID
        -email
        -Nome
        -Cognome
        -Ruolo
        -Password
        -Indirizzo
        -Numero
        -Genere
        -Img.
        -(ID-Prodotti)
     */

    private Long id;

    @NotNull(message = "{firstname.notempty}")
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @Past
    private LocalDate birthDate;

    private Gender gender;


}
