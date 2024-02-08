package com.example.ofppt.Service;


import com.example.ofppt.Dto.StagiaireDTO;

import com.example.ofppt.Entite.Filiale;
import com.example.ofppt.Entite.Groupe;
import com.example.ofppt.Entite.ModulePrograme;
import com.example.ofppt.Entite.Stagiaire;

import com.example.ofppt.Mapper.StagiaireMapper;

import com.example.ofppt.Repository.FilialeRepository;
import com.example.ofppt.Repository.GroupeRepository;
import com.example.ofppt.Repository.ModuleProgrameRepository;
import com.example.ofppt.Repository.StagiaireRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StagiaireService {
    private StagiaireRepository stagiaireRepository;

    private FilialeRepository filialeRepository;
    private ModuleProgrameRepository moduleProgrameRepository;

    private GroupeRepository groupeRepository;
    private StagiaireMapper stagiaireMapper;


    @Autowired
    public StagiaireService(StagiaireRepository stagiaireRepository, StagiaireMapper stagiaireMapper,
    GroupeRepository groupeRepository, FilialeRepository filialeRepository) {
        this.stagiaireRepository = stagiaireRepository;
        this.stagiaireMapper = stagiaireMapper;
        this.groupeRepository = groupeRepository;
        this.filialeRepository = filialeRepository;
    }

    public StagiaireDTO ajouterStagiaire(StagiaireDTO stagiaireDTO) {
        // Convertir StagiaireDTO en entité Stagiaire
        Stagiaire nouveauStagiaire = stagiaireMapper.toEntity(stagiaireDTO);

        // Rechercher le groupe par son nom s'il est présent
        if (stagiaireDTO.getGroupe() != null) {
            Groupe groupe = groupeRepository.findByNom(stagiaireDTO.getGroupe());
            nouveauStagiaire.setGroupe(groupe);
        }

        // Rechercher la filiale par son nom s'il est présent
        if (stagiaireDTO.getFiliale() != null) {
            Filiale filiale = filialeRepository.findByNom(stagiaireDTO.getFiliale());
            nouveauStagiaire.setFiliale(filiale);
        }

        // Enregistrer le stagiaire dans la base de données
        Stagiaire stagiaireEnregistre = stagiaireRepository.save(nouveauStagiaire);

        // Convertir l'entité Stagiaire en StagiaireDTO pour la réponse
        return stagiaireMapper.toDTO(stagiaireEnregistre);
    }

    //la 1er methode pour recupery tous les stagiaire (getAll)

    public List<StagiaireDTO> getAllStagiaire() {
        List<Stagiaire> stagiaires = stagiaireRepository.findStagiaireDTOBy();
        return stagiaires.stream()
                .map(stagiaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    //enregistre tous les stagiaire qui on ete saiasie

    public void saveStagiaireDto(StagiaireDTO stagiaireDTO) {
        // Utilisez le mapper pour convertir StagiaireDTO en Stagiaire
        Stagiaire stagiaire = stagiaireMapper.toEntity(stagiaireDTO);
        stagiaireRepository.save(stagiaire);
    }

    //ramene un seul Stagiaire by id

    public StagiaireDTO getStagiaireById(Long id) {
        Optional<Stagiaire> stagiaireOptional = stagiaireRepository.findById(id);
        return stagiaireOptional.map(stagiaireMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("merci de noter que ce Stagiaire il n'exsiste pas"));


    }



    //modifier un stagiaire les info que tu veux le modifier

    public StagiaireDTO updateStagiaire (Long id , StagiaireDTO updateStagiaire){
        Optional<Stagiaire> stagiaireOptional = stagiaireRepository.findById(id);
    if (stagiaireOptional.isPresent()){
        Stagiaire stagiaire = stagiaireOptional.get();
        stagiaire.setNom(updateStagiaire.getNom());
        stagiaire.setPrenom(updateStagiaire.getPrenom());
        stagiaire.setAdress(updateStagiaire.getAdress());
        stagiaire.setDiplome(updateStagiaire.getDiplome());
        stagiaire.setEmail(updateStagiaire.getEmail());
        stagiaire.setAge(updateStagiaire.getAge());
        stagiaire.setFonction(updateStagiaire.getFonction());
        stagiaire.setCin(updateStagiaire.getCin());

        if (updateStagiaire.getFiliale() != null) {
            Filiale filiale = filialeRepository.findByNom(updateStagiaire.getFiliale());
            stagiaire.setFiliale(filiale);
        }

        // Mise à jour des modules de programme
        if (updateStagiaire.getModuleProgrames() != null) {
            Set<ModulePrograme> modules = updateStagiaire.getModuleProgrames().stream()
                    .map(moduleId -> moduleProgrameRepository.findById(moduleId).orElse(null))
                    .collect(Collectors.toSet());
            stagiaire.setModuleProgrames(modules);
        }

        // Mise à jour du groupe
        if (updateStagiaire.getGroupe() != null) {
            Groupe groupe = groupeRepository.findByNom(updateStagiaire.getGroupe());
            stagiaire.setGroupe(groupe);
        }

        return stagiaireMapper.toDTO(stagiaireRepository.save(stagiaire));
    }else
        throw  new IllegalArgumentException("merci de noter que ce Stagiaire il n'exsiste pas");
    }

    //suprimer un stagiaire dans la liste

    public void deleteStagiare(Long id ){
        stagiaireRepository.deleteById(id);
    }

    //faire une recherche d'un Stagiaire avec le nom

    public List<StagiaireDTO> searchStagiaire(String nom) {
        List<Stagiaire> stagiaires = stagiaireRepository.findAllStagiaireDTOByNom(nom);
        // Utilisez le mapper pour convertir la liste d'entités en une liste de DTO
        return stagiaires.stream()
                .map(stagiaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Stagiaire uploadDocument(Long stagiaireId, StagiaireDTO stagiaireDTO) {
        Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId)
                .orElseThrow(() -> new EntityNotFoundException("Stagiaire not found with id: " + stagiaireId));

        stagiaire.setHasDocuments(true);
        stagiaire.setBase64File(stagiaireDTO.getBase64File());
        // Save other relevant information to the Stagiaire entity
        // ...

        return stagiaireRepository.save(stagiaire);
    }





}
