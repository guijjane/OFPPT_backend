package com.example.ofppt.Dto;

import lombok.Data;

import java.util.Optional;
import java.util.Set;

@Data
public class ProfesseurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private Long age;
    private String telephone;
    private String email;
    private String  adress;
    private  String specialite;


    private Set<Long> moduleProgrames;
    private Set<Long> stagiaires;
    private Set<Long> filiales;

    public Set<Long> getModuleProgrames() {
        return moduleProgrames;
    }
    public Set<Long> getStagiaires() {
        return stagiaires;
    }
    public Set<Long> getFiliales() {
        return filiales;
    }


}
