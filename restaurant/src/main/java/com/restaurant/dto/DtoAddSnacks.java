package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAddSnacks {

    private Long snackId;

    private String snackName;

    private String description;

    private Integer price;
}
