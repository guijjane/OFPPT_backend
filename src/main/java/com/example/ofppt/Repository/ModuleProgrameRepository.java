package com.example.ofppt.Repository;

import com.example.ofppt.Entite.Groupe;
import com.example.ofppt.Entite.ModulePrograme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModuleProgrameRepository extends JpaRepository<ModulePrograme,Long> {

    List<ModulePrograme>findModuleProgrameDTOBy(); // Cette méthode récupère tous les ModulePrograme
    Optional<ModulePrograme> findModuleProgrameDTOById(Long id);
    List<ModulePrograme> findModuleProgrameDTOByNom(String nom); // Cette méthode récupère un ModulePrograme par son nom
}
