package com.estudo.space.domain.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SatelliteDTO {
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Boolean natural;
}
