package com.example.storeeverything.Dtos;

import jakarta.validation.constraints.DecimalMin;
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
    @Size(min=3, max = 20)
    @Pattern( regexp = "[A-Z][a-ząęóśżźćń]*", message = "Tylko pierwsza litera może być wielka")
    private String firstName;
    @Size(min=3, max = 50)
    @NonNull
    @Pattern( regexp = "[A-Z][a-ząęóśżźćń]*", message = "Tylko pierwsza litera może być wielka")
    private String surname;
    @NonNull
    @Size(min=3, max = 20)
    @Pattern( regexp = "[a-ząęóśżźćń]*", message = "Tylko małe litery")
    private String login;
    @NonNull
    @Size(min=5)
    private String password;
    @DecimalMin("18")
    @NonNull
    private Integer age;

}
