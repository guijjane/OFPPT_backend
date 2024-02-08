package com.example.ofppt.Service;

import com.example.ofppt.Dto.ModuleProgrameDTO;
import com.example.ofppt.Entite.ModulePrograme;
import com.example.ofppt.Mapper.ModuleProgrameMapper;
import com.example.ofppt.Repository.ModuleProgrameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModuleProgrameService {

    private  ModuleProgrameRepository moduleProgrameRepository;
    private  ModuleProgrameMapper moduleProgrameMapper;


    public List<ModuleProgrameDTO> getAllModuleProgrames() {
        List<ModulePrograme> moduleProgrames = moduleProgrameRepository.findModuleProgrameDTOBy();
        return moduleProgrames.stream()
                .map(moduleProgrameMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ModuleProgrameDTO getModuleProgrameById(Long id) {
        Optional<ModulePrograme> moduleProgrameOptional = moduleProgrameRepository.findModuleProgrameDTOById(id);
        return moduleProgrameOptional.map(moduleProgrameMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Ce ModulePrograme n'existe pas"));
    }

    public List<ModuleProgrameDTO> getModuleProgramesByNom(String nom) {
        List<ModulePrograme> moduleProgrames = moduleProgrameRepository.findModuleProgrameDTOByNom(nom);
        return moduleProgrames.stream()
                .map(moduleProgrameMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ModuleProgrameDTO createModulePrograme(ModuleProgrameDTO moduleProgrameDTO) {
        ModulePrograme modulePrograme = moduleProgrameMapper.toEntity(moduleProgrameDTO);
        modulePrograme = moduleProgrameRepository.save(modulePrograme);
        return moduleProgrameMapper.toDTO(modulePrograme);
    }

    public ModuleProgrameDTO updateModulePrograme(Long id, ModuleProgrameDTO updateModulePrograme) {
        Optional<ModulePrograme> moduleProgrameOptional = moduleProgrameRepository.findById(id);

        if (moduleProgrameOptional.isPresent()) {
            ModulePrograme modulePrograme = moduleProgrameOptional.get();
            modulePrograme.setNom(updateModulePrograme.getNom());
            return moduleProgrameMapper.toDTO(moduleProgrameRepository.save(modulePrograme));
        } else {
            throw new IllegalArgumentException("Ce ModulePrograme n'existe pas");
        }
    }

    public void deleteModulePrograme(Long id) {
        moduleProgrameRepository.deleteById(id);
    }
}
