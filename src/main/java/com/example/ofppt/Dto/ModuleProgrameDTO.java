package com.example.ofppt.Dto;

import lombok.Data;

import java.util.Set;

@Data
public class ModuleProgrameDTO {
    private Long id;
    private String nom;
    private Long professeurId;
    //private Long filialeId;
    private Set<Long> filialeIds;

}
