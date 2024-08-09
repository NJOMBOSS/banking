package tech.fonke.banking.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tech.fonke.banking.models.User;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotNull(message = "Le prènom ne doit pas être vide")
    @NotEmpty(message = "Le prènom ne doit pas être vide")
    @NotBlank(message = "Le prènom ne doit pas être vide")
    private String firstname;

    @NotNull(message = "Le nom ne doit pas être vide")
    @NotEmpty(message = "Le nom ne doit pas être vide")
    @NotBlank(message = "Le nom ne doit pas être vide")
    private String lastname;

    @NotNull(message = "L'email ne doit pas être vide")
    @NotEmpty(message = "L'emailne doit pas être vide")
    @NotBlank(message = "L'email ne doit pas être vide")
    @Email(message = "L'email n'est pas conforme")
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8, max=16, message = "Le mot de passe doit être entre 8 et 16 caractères")
    private String password;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
