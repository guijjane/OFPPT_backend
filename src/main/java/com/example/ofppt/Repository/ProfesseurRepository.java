package com.example.ofppt.Repository;

import com.example.ofppt.Entite.Professeur;
import com.example.ofppt.Entite.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesseurRepository extends JpaRepository<Professeur,Long> {

    List<Professeur> findProfesseurBy(); // Cette méthode récupère tous les stagiaires

    Optional<Professeur> findProfesseurDTOById(Long id); // Cette méthode récupère un stagiaire par son identifiant

    List<Professeur> findAllProfesseurDTOByNom(String nom); // Cette méthode récupère tous les stagiaires par nom


}
