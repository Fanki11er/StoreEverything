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
    @Size(min=3, max = 20, message = "Minimalnie 3 znaki, maksymalnie 20 znaków")
    @Pattern( regexp = "[A-Z][a-ząęóśżźćń]*", message = "Pierwsza litera musi być wielka, pozostałe muszą byc małe")
    private String firstName;
    @Size(min=3, max = 50, message = "Minimalnie 3 znaki, maksymalnie 50 znaków")
    @NonNull
    @Pattern( regexp = "[A-Z][a-ząęóśżźćń]*", message = "Pierwsza litera musi być wielka, pozostałe muszą byc małe")
    private String surname;
    @NonNull
    @Size(min=3, max = 20, message = "Minimalnie 3 znaki, maksymalnie 20 znaków")
    @Pattern( regexp = "[a-ząęóśżźćń]*", message = "Tylko małe litery")
    private String login;
    @NonNull
    @Size(min=5, message = "Minimalna długość hasła to 5 znaków")
    private String password;
    @DecimalMin(value = "18", message = "Musisz mieć minimum 18 lat")
    @NonNull
    private Integer age;

}
