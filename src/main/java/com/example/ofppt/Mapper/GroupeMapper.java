package com.example.ofppt.Mapper;

import com.example.ofppt.Dto.GroupeDTO;
import com.example.ofppt.Entite.Filiale;
import com.example.ofppt.Entite.Groupe;
import com.example.ofppt.Entite.Professeur;
import com.example.ofppt.Repository.FilialeRepository;
import com.example.ofppt.Repository.ProfesseurRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GroupeMapper {
    /*public GroupeDTO toDTO(Groupe groupe) {
        GroupeDTO groupeDTO = new GroupeDTO();
        BeanUtils.copyProperties(groupe, groupeDTO);
        return groupeDTO;
    }

    public static Groupe toEntity(GroupeDTO groupeDTO) {
        Groupe groupe = new Groupe();
        BeanUtils.copyProperties(groupeDTO, groupe);
        return groupe;
    }*/

    private static FilialeRepository filialeRepository;
    private static ProfesseurRepository professeurRepository;

    public GroupeMapper(FilialeRepository filialeRepository, ProfesseurRepository professeurRepository) {
        this.filialeRepository = filialeRepository;
        this.professeurRepository = professeurRepository;
    }

    public GroupeDTO toDTO(Groupe groupe) {
        GroupeDTO groupeDTO = new GroupeDTO();
        BeanUtils.copyProperties(groupe, groupeDTO);

        // Ajouter la logique de mappage pour la filiale
        if (groupe.getFiliale() != null) {
            groupeDTO.setFilialeId(groupe.getFiliale().getId());
        }

        // Ajouter la logique de mappage pour le professeur
        if (groupe.getProfesseur() != null) {
            groupeDTO.setProfesseurId(groupe.getProfesseur().getId());
        }

        return groupeDTO;
    }

    public static Groupe toEntity(GroupeDTO groupeDTO) {
        Groupe groupe = new Groupe();
        BeanUtils.copyProperties(groupeDTO, groupe);

        // Ajouter la logique de mappage pour la filiale
        if (groupeDTO.getFilialeId() != null) {
            Filiale filiale = filialeRepository.findById(groupeDTO.getFilialeId()).orElse(null);
            groupe.setFiliale(filiale);
        }

        // Ajouter la logique de mappage pour le professeur
        if (groupeDTO.getProfesseurId() != null) {
            Professeur professeur = professeurRepository.findById(groupeDTO.getProfesseurId()).orElse(null);
            groupe.setProfesseur(professeur);
        }

        return groupe;
    }

}
