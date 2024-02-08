package com.example.ofppt.Controller;

import com.example.ofppt.Dto.FilialeDTO;
import com.example.ofppt.Service.FilialeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/")
public class FilialeController {
    private FilialeService filialeService;

    @GetMapping("/ListFiliale")
    public List<FilialeDTO> listFiliale() {
        return filialeService.getAllFiliales();
    }

    @GetMapping("/uneFiliale/{id}")
    public FilialeDTO uneFiliale(@PathVariable Long id) {
        return filialeService.getFilialeById(id);
    }

    @GetMapping("/uneFiliale/{nom}")
    public List<FilialeDTO> uneFiliale(@PathVariable String nom) {
        return filialeService.getFilialesBynom(nom);
    }

    @PostMapping("/saveFiliale")
    public FilialeDTO saveFiliale(@RequestBody FilialeDTO filialeDTO) {
        filialeService.createFiliale(filialeDTO);
        return filialeDTO;
    }

    @PutMapping("/updateFiliale/{id}")
    public FilialeDTO updateFiliale(@PathVariable Long id, @RequestBody FilialeDTO filialeDTO) {
        return filialeService.updateFiliale(id, filialeDTO);
    }

    @DeleteMapping("/deleteFiliale/{id}")
    public void deleteFiliale(@PathVariable Long id) {
        filialeService.deleteFiliale(id);
    }

    @GetMapping("/searchFiliale")
    public List<FilialeDTO> searchFiliale(@RequestParam("nom") String nom) {
        return filialeService.getFilialesByNom(nom);
    }
}

