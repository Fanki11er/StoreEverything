package com.example.storeeverything.Dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDto {
    @NonNull
    @Size(min=3, max = 20, message = "Minimalna długość to {min} znaki, maksymalna długość to {max} znaków")
    @Pattern( regexp = "[A-Z][a-ząęóśżźćń]*", message = "Tylko pierwsza litera może być wielka")
    private String firstName;
    @Size(min=3, max = 50, message = "Minimalna długość to {min} znaki, maksymalna długość to {max} znaków")
    @NonNull
    @Pattern( regexp = "[A-Z][a-ząęóśżźćń]*", message = "Tylko pierwsza litera może być wielka")
    private String surname;
    @NonNull
    @Size(min=3, max = 20, message = "Minimalna długość to {min} znaki, maksymalna długość to {max} znaków")
    @Pattern( regexp = "[a-ząęóśżźćń]*", message = "Tylko małe litery")
    private String login;
    @NonNull
    @Size(min=5, message = "Minimalna długość to {min} znaków")
    private String password;
    @Email
    @NonNull
    private String email;

}
