package com.example.storeeverything.Dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LinkedItemDto {
    @NonNull()
    Long itemId;
}
