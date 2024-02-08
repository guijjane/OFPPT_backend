package com.example.ofppt.Repository;

import com.example.ofppt.Dto.StagiaireDTO;
import com.example.ofppt.Entite.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StagiaireRepository extends JpaRepository<Stagiaire,Long> {

    List<Stagiaire> findStagiaireDTOBy(); // Cette méthode récupère tous les stagiaires

    Optional<Stagiaire> findStagiaireDTOById(Long id); // Cette méthode récupère un stagiaire par son identifiant

    List<Stagiaire> findAllStagiaireDTOByNom(String nom); // Cette méthode récupère tous les stagiaires par nom

    List<Stagiaire> findByHasDocumentsTrue();
}
