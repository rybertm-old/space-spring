package com.estudo.space.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SatelliteDTO {
    private Integer id;

    private String name;

    private Boolean natural;
}
