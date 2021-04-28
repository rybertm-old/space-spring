package com.estudo.space.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GravityDTO {
    private Integer id;

    private Double force;

    private Double radiation;

    private Integer planetId;
}
