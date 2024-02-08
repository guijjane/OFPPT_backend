package com.example.ofppt.Dto;


import com.example.ofppt.Entite.Stagiaire;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Set;

@Data
public class StagiaireDTO {
    private Long id;
    private String nom;
    private String prenom;
    private Long age;
    private String telephone;
    private String email;
    private String cin;
    private String  adress;
    private String  diplome;
    private String  fonction;
    private String filiale;
    private String groupe;
    private Set<Long> moduleProgrames;
    private String base64File;

    public Set<Long> getModuleProgrames() {
        return moduleProgrames;
    }

    private boolean hasDocuments;

    public String getBase64File() {
        return base64File;
    }

    public void setBase64File(String base64File) {
        this.base64File = base64File;
    }

}
