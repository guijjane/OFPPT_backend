package com.example.ofppt.Service;

import com.example.ofppt.Dto.ProfesseurDTO;
import com.example.ofppt.Dto.StagiaireDTO;
import com.example.ofppt.Entite.Filiale;
import com.example.ofppt.Entite.ModulePrograme;
import com.example.ofppt.Entite.Professeur;
import com.example.ofppt.Entite.Stagiaire;
import com.example.ofppt.Mapper.ProfesseurMapper;
import com.example.ofppt.Repository.FilialeRepository;
import com.example.ofppt.Repository.ModuleProgrameRepository;
import com.example.ofppt.Repository.ProfesseurRepository;
import com.example.ofppt.Repository.StagiaireRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfesseurService {

    private  ProfesseurRepository professeurRepository;
    private  ProfesseurMapper professeurMapper;
    private StagiaireRepository stagiaireRepository;
    private FilialeRepository filialeRepository;
    private ModuleProgrameRepository moduleProgrameRepository;


    public List<ProfesseurDTO> getAllProfesseurs() {
        List<Professeur> professeurs = professeurRepository.findProfesseurBy();
        return professeurs.stream()
                .map(professeurMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProfesseurDTO getProfesseurById(Long id) {
        Optional<Professeur> professeurOptional = professeurRepository.findById(id);
        return professeurOptional.map(professeurMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Ce Professeur n'existe pas"));
    }

    public List<ProfesseurDTO> getProfesseursByNom(String nom) {
        List<Professeur> professeurs = professeurRepository.findAllProfesseurDTOByNom(nom);
        return professeurs.stream()
                .map(professeurMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProfesseurDTO createProfesseur(ProfesseurDTO professeurDTO) {
        Professeur professeur = professeurMapper.toEntity(professeurDTO);
        professeur = professeurRepository.save(professeur);
        return professeurMapper.toDTO(professeur);
    }

    public ProfesseurDTO updateProfesseur(Long id, ProfesseurDTO updateProfesseur) {
        Optional<Professeur> professeurOptional = professeurRepository.findProfesseurDTOById(id);

        if (professeurOptional.isPresent()) {
            Professeur professeur = professeurOptional.get();
            professeur.setNom(updateProfesseur.getNom());
            professeur.setPrenom(updateProfesseur.getPrenom());
            professeur.setAge(updateProfesseur.getAge());
            professeur.setTelephone(updateProfesseur.getTelephone());
            professeur.setEmail(updateProfesseur.getEmail());
            professeur.setAdress(updateProfesseur.getAdress());
            professeur.setSpecialite(updateProfesseur.getSpecialite());
            if (updateProfesseur.getStagiaires() != null) {
                Set<Stagiaire> stagiaires = updateProfesseur.getStagiaires().stream()
                        .map(stagiaireId -> stagiaireRepository.findById(stagiaireId).orElse(null))
                        .collect(Collectors.toSet());
                professeur.setStagiaires(stagiaires);
            }

            // Mettez à jour les associations avec les filiales
            if (updateProfesseur.getFiliales() != null) {
                Set<Filiale> filiales = updateProfesseur.getFiliales().stream()
                        .map(filialeId -> filialeRepository.findById(filialeId).orElse(null))
                        .collect(Collectors.toSet());
                professeur.setFiliales(filiales);
            }

            // Mettez à jour les associations avec les modules de programme
            if (updateProfesseur.getModuleProgrames() != null) {
                Set<ModulePrograme> modules = updateProfesseur.getModuleProgrames().stream()
                        .map(moduleId -> moduleProgrameRepository.findById(moduleId).orElse(null))
                        .collect(Collectors.toSet());
                professeur.setModuleProgrames(modules);
            }
            return professeurMapper.toDTO(professeurRepository.save(professeur));
        } else {
            throw new IllegalArgumentException("Ce Professeur n'existe pas");
        }
    }

    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }




}
