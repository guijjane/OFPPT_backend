package com.example.ofppt.Controller;

import com.example.ofppt.Dto.GroupeDTO;
import com.example.ofppt.Service.GroupeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/")
public class GroupeController {
    private GroupeService groupeService;

    @GetMapping("/ListGroupe")
    public List<GroupeDTO> listGroupe() {
        return groupeService.getAllGroupes();
    }

    @GetMapping("/unGroupe/{id}")
    public GroupeDTO unGroupe(@PathVariable Long id) {
        return groupeService.getGroupeById(id);
    }

    @PostMapping("/saveGroupe")
    public GroupeDTO saveGroupe(@RequestBody GroupeDTO groupeDTO) {
        groupeService.createGroupe(groupeDTO);
        return groupeDTO;
    }

    @PutMapping("/updateGroupe/{id}")
    public GroupeDTO updateGroupe(@PathVariable Long id, @RequestBody GroupeDTO groupeDTO) {
        return groupeService.updateGroupe(id, groupeDTO);
    }

    @DeleteMapping("/deleteGroupe/{id}")
    public void deleteGroupe(@PathVariable Long id) {
        groupeService.deleteGroupe(id);
    }

    @GetMapping("/searchGroupe")
    public List<GroupeDTO> searchGroupe(@RequestParam("nom") String nom) {
        return groupeService.getGroupesByNom(nom);
    }
}

