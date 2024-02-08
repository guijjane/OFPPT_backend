package com.example.ofppt.Mapper;

import com.example.ofppt.Dto.ProfesseurDTO;
import com.example.ofppt.Entite.Filiale;
import com.example.ofppt.Entite.ModulePrograme;
import com.example.ofppt.Entite.Professeur;
import com.example.ofppt.Entite.Stagiaire;
import com.example.ofppt.Repository.FilialeRepository;
import com.example.ofppt.Repository.ModuleProgrameRepository;
import com.example.ofppt.Repository.StagiaireRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfesseurMapper {
/*public ProfesseurDTO toDTO(Professeur professeur){
    ProfesseurDTO professeurDTO = new ProfesseurDTO();
    BeanUtils.copyProperties(professeur,professeurDTO);
    return professeurDTO;
}
public static Professeur toEntity(ProfesseurDTO professeurDTO) {
    Professeur professeur = new Professeur();
    BeanUtils.copyProperties(professeurDTO, professeur);
    return professeur;
}*/
private ModuleProgrameRepository moduleProgrameRepository;
    private  StagiaireRepository stagiaireRepository;
    private  FilialeRepository filialeRepository;

    public ProfesseurMapper(ModuleProgrameRepository moduleProgrameRepository, StagiaireRepository stagiaireRepository,
                            FilialeRepository filialeRepository) {
        this.moduleProgrameRepository = moduleProgrameRepository;
        this.stagiaireRepository = stagiaireRepository;
        this.filialeRepository = filialeRepository;
    }

    public ProfesseurDTO toDTO(Professeur professeur) {
        ProfesseurDTO professeurDTO = new ProfesseurDTO();
        BeanUtils.copyProperties(professeur, professeurDTO);

        professeurDTO.setModuleProgrames(professeur.getModuleProgrames().stream()
                .map(ModulePrograme::getId)
                .collect(Collectors.toSet()));
        professeurDTO.setStagiaires(professeur.getStagiaires().stream()
                .map(Stagiaire::getId)
                .collect(Collectors.toSet()));
        professeurDTO.setFiliales(professeur.getFiliales().stream()
                .map(Filiale::getId)
                .collect(Collectors.toSet()));





        return professeurDTO;
    }

    public Professeur toEntity(ProfesseurDTO professeurDTO) {
        Professeur professeur = new Professeur();
        BeanUtils.copyProperties(professeurDTO, professeur);

        Set<ModulePrograme> modules = professeurDTO.getModuleProgrames().stream()
                .map(moduleId -> moduleProgrameRepository.findById(moduleId).orElse(null))
                .collect(Collectors.toSet());
        professeur.setModuleProgrames(modules);

        Set<Stagiaire> stagiaires = professeurDTO.getStagiaires().stream()
                .map(stagiaireId -> stagiaireRepository.findById(stagiaireId).orElse(null))
                .collect(Collectors.toSet());
        professeur.setStagiaires(stagiaires);

        Set<Filiale> filiales = professeurDTO.getFiliales().stream()
                .map(filialeId -> filialeRepository.findById(filialeId).orElse(null))
                .collect(Collectors.toSet());
        professeur.setFiliales(filiales);



        return professeur;
    }

}
