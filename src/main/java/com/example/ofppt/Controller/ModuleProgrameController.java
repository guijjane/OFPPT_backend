package com.example.ofppt.Controller;

import com.example.ofppt.Dto.ModuleProgrameDTO;
import com.example.ofppt.Service.ModuleProgrameService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ModuleProgrameController {
    private ModuleProgrameService moduleProgrameService;

    @GetMapping("/ListModulePrograme")
    public List<ModuleProgrameDTO> listModulePrograme() {
        return moduleProgrameService.getAllModuleProgrames();
    }

    @GetMapping("/unModulePrograme/{id}")
    public ModuleProgrameDTO unModulePrograme(@PathVariable Long id) {
        return moduleProgrameService.getModuleProgrameById(id);
    }

    @PostMapping("/saveModulePrograme")
    public ModuleProgrameDTO saveModulePrograme(@RequestBody ModuleProgrameDTO moduleProgrameDTO) {
        moduleProgrameService.createModulePrograme(moduleProgrameDTO);
        return moduleProgrameDTO;
    }

    @PutMapping("/updateModulePrograme/{id}")
    public ModuleProgrameDTO updateModulePrograme(@PathVariable Long id, @RequestBody ModuleProgrameDTO moduleProgrameDTO) {
        return moduleProgrameService.updateModulePrograme(id, moduleProgrameDTO);
    }

    @DeleteMapping("/deleteModulePrograme/{id}")
    public void deleteModulePrograme(@PathVariable Long id) {
        moduleProgrameService.deleteModulePrograme(id);
    }

    @GetMapping("/searchModulePrograme")
    public List<ModuleProgrameDTO> searchModulePrograme(@RequestParam("nom") String nom) {
        return moduleProgrameService.getModuleProgramesByNom(nom);
    }
}

