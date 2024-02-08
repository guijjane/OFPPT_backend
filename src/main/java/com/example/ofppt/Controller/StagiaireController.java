package com.example.ofppt.Controller;

import com.example.ofppt.Dto.StagiaireDTO;
import com.example.ofppt.Entite.Stagiaire;
import com.example.ofppt.Service.StagiaireService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/")

public class StagiaireController {
   private StagiaireService stagiaireService;

   //afficher toute la liste des stagiaire

    @PostMapping("/ajout")
    public ResponseEntity<StagiaireDTO> ajouterStagiaire(@RequestBody StagiaireDTO stagiaireDTO) {
        StagiaireDTO nouveauStagiaire = stagiaireService.ajouterStagiaire(stagiaireDTO);
        return new ResponseEntity<>(nouveauStagiaire, HttpStatus.CREATED);
    }

    @GetMapping("/ListStagiaire")
    public List<StagiaireDTO> listStagiaire(){
        return stagiaireService.getAllStagiaire();
    }

    //afficher un seul stagiaire avec son id

    @GetMapping("/unStagiaire/{id}")
    public StagiaireDTO unStagiaire(@PathVariable Long id){
        return stagiaireService.getStagiaireById(id);

    }

    //enregistre un stagiaire dans la base

    @PostMapping("/saveStagiaire")
    public StagiaireDTO saveStagiaire(@RequestBody StagiaireDTO stagiaireDTO){
        stagiaireService.saveStagiaireDto(stagiaireDTO);
     return stagiaireDTO;
    }

    //pour modifier un prospect

    @PutMapping("/updateStagiaire/{id}")
    public  StagiaireDTO updateStagiare(@PathVariable Long id,@RequestBody StagiaireDTO stagiaireDTO){
        return stagiaireService.updateStagiaire(id,stagiaireDTO);

    }


    //pour suprimer un stagiaire

    @DeleteMapping("/deleteStagiaire/{id}")
    public void deleteStagiaire(@PathVariable Long id){
         stagiaireService.deleteStagiare(id);
    }

    //pour rechercher un stagiaire

    @GetMapping("/searchStagiaire")
    public List<StagiaireDTO> searchStagiaire(@RequestParam("nom") String nom){
    return stagiaireService.searchStagiaire(nom);
    }

    @PostMapping("/{stagiaireId}/upload-document")
    public ResponseEntity<String> uploadDocument(@PathVariable Long stagiaireId,
                                                 @RequestParam("file") MultipartFile file) {
        //String base64File = Base64.getEncoder().encodeToString(file.getBytes());
        String base64FileId = "unique_file_identifier";
        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        stagiaireDTO.setBase64File(base64FileId);
        stagiaireService.uploadDocument(stagiaireId, stagiaireDTO);
        return new ResponseEntity<>("Document uploaded successfully.", HttpStatus.OK);
    }



}
