package com.example.storeeverything.Dtos;

import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Entities.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDto {

    @Size(min = 3, max = 20, message = "Tytuł powinna mieć długość od {min} do {max} znaków.")
    String title;

    @Size(min = 5, max = 500, message = "Długość treści powinna mieć długość od {min} do {max} znaków.")
    String content;
    @URL( message = "Nie prawidłowy format Url")
    String url;
    @NonNull()
    Category category;

}
