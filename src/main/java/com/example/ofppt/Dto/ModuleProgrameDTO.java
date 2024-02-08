package com.example.ofppt.Dto;

import lombok.Data;

@Data
public class ModuleDTO {
    private Long id;
    private String nom;
    private Long professeurId;
    private Long filialeId;
}
