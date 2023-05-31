package com.example.storeeverything.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortCookie {

    private SortBy sortBy = SortBy.DATA;
    private SortOrder sortOrder = SortOrder.MALEJĄCO;

}
