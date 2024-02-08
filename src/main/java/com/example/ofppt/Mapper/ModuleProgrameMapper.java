package com.example.ofppt.Mapper;

import com.example.ofppt.Dto.ModuleProgrameDTO;
import com.example.ofppt.Entite.Filiale;
import com.example.ofppt.Entite.Groupe;
import com.example.ofppt.Entite.ModulePrograme;
import com.example.ofppt.Entite.Professeur;
import com.example.ofppt.Repository.FilialeRepository;
import com.example.ofppt.Repository.ProfesseurRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ModuleProgrameMapper {
   /* public ModuleProgrameDTO toDTO(ModulePrograme modulePrograme) {
        ModuleProgrameDTO moduleProgrameDTO = new ModuleProgrameDTO();
        BeanUtils.copyProperties(modulePrograme, moduleProgrameDTO);
        return moduleProgrameDTO;
    }

    public static ModulePrograme toEntity(ModuleProgrameDTO moduleProgrameDTO) {
        ModulePrograme modulePrograme = new ModulePrograme();
        BeanUtils.copyProperties(moduleProgrameDTO, modulePrograme);

        return modulePrograme;
    }*/

    private ProfesseurRepository professeurRepository;
    private FilialeRepository filialeRepository;

    public ModuleProgrameMapper(ProfesseurRepository professeurRepository,
                                FilialeRepository filialeRepository) {
        this.professeurRepository = professeurRepository;
        this.filialeRepository = filialeRepository;
    }

    public ModuleProgrameDTO toDTO(ModulePrograme modulePrograme) {
        ModuleProgrameDTO moduleProgrameDTO = new ModuleProgrameDTO();
        BeanUtils.copyProperties(modulePrograme, moduleProgrameDTO);

        // Ajouter la logique de mappage pour le professeur
        if (modulePrograme.getProfesseur() != null) {
            moduleProgrameDTO.setProfesseurId(modulePrograme.getProfesseur().getId());
        }

        // Ajouter la logique de mappage pour la filiale
        /*if (modulePrograme.getGroupes() != null && !modulePrograme.getGroupes().isEmpty()) {
            Groupe groupe = modulePrograme.getGroupes().iterator().next();
            if (groupe.getFiliale() != null) {
                moduleProgrameDTO.setFilialeIds(groupe.getFiliale().getId());
            }
        }*/
        if (modulePrograme.getFiliales() != null) {
            moduleProgrameDTO.setFilialeIds(modulePrograme.getFiliales().stream()
                    .map(Filiale::getId)
                    .collect(Collectors.toSet()));
        }

        return moduleProgrameDTO;
    }

    public ModulePrograme toEntity(ModuleProgrameDTO moduleProgrameDTO) {
        ModulePrograme modulePrograme = new ModulePrograme();
        BeanUtils.copyProperties(moduleProgrameDTO, modulePrograme);

        // Ajouter la logique de mappage pour le professeur
        if (moduleProgrameDTO.getProfesseurId() != null) {
            Professeur professeur = professeurRepository.findById(moduleProgrameDTO.getProfesseurId()).orElse(null);
            modulePrograme.setProfesseur(professeur);
        }

        // Ajouter la logique de mappage pour la filiale
        /*if (moduleProgrameDTO.getFilialeIds() != null) {
            Filiale filiale = filialeRepository.findById(moduleProgrameDTO.getFilialeId()).orElse(null);
            // Assumez que le module est associé à un seul groupe, sinon, ajustez cette logique
            Groupe groupe = new Groupe();
            groupe.setFiliale(filiale);
            modulePrograme.setGroupes(Collections.singleton(groupe));
        }*/
        if (moduleProgrameDTO.getFilialeIds() != null) {
            Set<Filiale> filiales = moduleProgrameDTO.getFilialeIds().stream()
                    .map(filialeId -> filialeRepository.findById(filialeId).orElse(null))
                    .collect(Collectors.toSet());
            modulePrograme.setFiliales(filiales);
        }

        return modulePrograme;
    }
}