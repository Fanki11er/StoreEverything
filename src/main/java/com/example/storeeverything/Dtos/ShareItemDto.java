package com.example.storeeverything.Dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShareItemDto {

    @NonNull()
    Long itemId;

    @NonNull()
    Long userId;

}
