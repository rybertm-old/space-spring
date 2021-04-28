package com.estudo.space.domain.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GalaxyDTO {
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Long numberOfPlanets;

    private List<Integer> planetIds;
}
