package com.example.ofppt.Mapper;

import com.example.ofppt.Dto.ModuleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ModuleMapper {
    public static ModuleDTO toDTO(Module module) {
        ModuleDTO moduleDTO = new ModuleDTO();
        BeanUtils.copyProperties(module, moduleDTO);
        return moduleDTO;
    }

    public static Module toEntity(ModuleDTO moduleDTO) {
        Module module = new Module();
        BeanUtils.copyProperties(moduleDTO, module);

        return module;
    }
}
