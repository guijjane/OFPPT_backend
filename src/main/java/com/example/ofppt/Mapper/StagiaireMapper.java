package com.example.ofppt.Mapper;

import com.example.ofppt.Dto.StagiaireDTO;
import com.example.ofppt.Entite.ModulePrograme;
import com.example.ofppt.Entite.Stagiaire;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class StagiaireMapper {
   /* public StagiaireDTO toDTO(Stagiaire stagiaire) {
        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        BeanUtils.copyProperties(stagiaire, stagiaireDTO);

        return stagiaireDTO;
    }*/


    public StagiaireDTO toDTO(Stagiaire stagiaire) {
        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        BeanUtils.copyProperties(stagiaire, stagiaireDTO);

        // Ajoutez ces lignes pour mapper les IDs
        stagiaireDTO.setFiliale(stagiaire.getFiliale() != null ? stagiaire.getFiliale().getNom() : null);
        stagiaireDTO.setGroupe(stagiaire.getGroupe() != null ? stagiaire.getGroupe().getNom() : null);

        if (stagiaire.getModuleProgrames() != null) {
            stagiaireDTO.setModuleProgrames(
                    stagiaire.getModuleProgrames().stream().map(ModulePrograme::getId).collect(Collectors.toSet())
            );
        }

        return stagiaireDTO;
    }

    public static Stagiaire toEntity(StagiaireDTO stagiaireDTO) {
        Stagiaire stagiaire = new Stagiaire();
        BeanUtils.copyProperties(stagiaireDTO, stagiaire);
        return stagiaire;
    }


}
