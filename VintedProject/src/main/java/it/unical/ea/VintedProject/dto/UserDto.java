package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.dto.enumeration.Gender;
import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
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

    @NotNull(message = "nickname.not.empty")
    private String nickName;

    @NotNull(message = "firstname.not.empty")
    private String firstName;

    @NotNull(message = "lastname.not.empty")
    private String lastName;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] userImage;

    @Email(message = "email.not.valid")
    private String email;

    @Past(message = "birthdate.past" )
    private String birthDate;

    private Gender gender;
    private String addressStreet;
    private Integer addressNumber;
    private String addressCity;
    private Integer addressCap;
    private String addressState;
    private String addressRegion;

}
