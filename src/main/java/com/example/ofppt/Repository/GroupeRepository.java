package com.example.ofppt.Repository;


import com.example.ofppt.Dto.GroupeDTO;
import com.example.ofppt.Entite.Filiale;
import com.example.ofppt.Entite.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupeRepository extends JpaRepository<Groupe,Long> {

    List<Groupe> findGroupeDTOBy(); // Cette méthode récupère tous les filiales
    Optional<Groupe>findGroupeDTOById(Long id);
    List<Groupe> findGroupeDTOByNom(String nom); // Cette méthode récupère un filiales par son nom

    Groupe findByNom(String nom);
}
