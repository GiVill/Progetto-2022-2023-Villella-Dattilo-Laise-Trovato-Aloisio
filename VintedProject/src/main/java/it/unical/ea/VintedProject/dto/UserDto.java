package it.unical.ea.VintedProject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.unical.ea.VintedProject.data.entities.Address;
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

    private Long id;

    @NotNull(message = "nickname.notempty")
    private String nickName;

    @NotNull(message = "firstname.notempty")
    private String firstName;


    private String lastName;

    @Email(message = "email.valid")
    private String email;

    @Past(message = "birthdate.past" )
    private LocalDate birthDate;

    private Gender gender;
    private String addressStreet;
    private Integer addressNumber;
    private String addressCity;
    private Integer addressCap;
    private String addressState;
    private String addressRegion;

}
