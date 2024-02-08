package com.example.ofppt.Controller;

import com.example.ofppt.Dto.ProfesseurDTO;
import com.example.ofppt.Service.ProfesseurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ProfesseurController {
    private ProfesseurService professeurService;

    @GetMapping("/ListProfesseur")
    public List<ProfesseurDTO> listProfesseur() {
        return professeurService.getAllProfesseurs();
    }

    @GetMapping("/unProfesseur/{id}")
    public ProfesseurDTO unProfesseur(@PathVariable Long id) {
        return professeurService.getProfesseurById(id);
    }

    @PostMapping("/saveProfesseur")
    public ProfesseurDTO saveProfesseur(@RequestBody ProfesseurDTO professeurDTO) {
        professeurService.createProfesseur(professeurDTO);
        return professeurDTO;
    }

    @PutMapping("/updateProfesseur/{id}")
    public ProfesseurDTO updateProfesseur(@PathVariable Long id, @RequestBody ProfesseurDTO professeurDTO) {
        return professeurService.updateProfesseur(id, professeurDTO);
    }

    @DeleteMapping("/deleteProfesseur/{id}")
    public void deleteProfesseur(@PathVariable Long id) {
        professeurService.deleteProfesseur(id);
    }

    @GetMapping("/searchProfesseur")
    public List<ProfesseurDTO> searchProfesseur(@RequestParam("nom") String nom) {
        return professeurService.getProfesseursByNom(nom);
    }
}

