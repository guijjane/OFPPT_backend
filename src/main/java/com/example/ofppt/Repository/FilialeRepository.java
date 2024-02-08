package com.example.ofppt.Repository;


import com.example.ofppt.Dto.FilialeDTO;
import com.example.ofppt.Entite.Filiale;
import com.example.ofppt.Entite.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  FilialeRepository extends JpaRepository<Filiale,Long> {
    List<Filiale> findFilialeDTOBy(); // Cette méthode récupère tous les filiales
    Optional<Filiale> findFilialeDTOById(Long id);// Cette méthode récupère un filiales par son identifiant
    List<Filiale> findFilialeDTOByNom(String nom); // Cette méthode récupère un filiales par son nom


    Filiale findByNom(String nom);
}
