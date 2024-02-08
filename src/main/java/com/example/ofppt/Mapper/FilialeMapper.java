package com.example.ofppt.Mapper;

import com.example.ofppt.Dto.FilialeDTO;
import com.example.ofppt.Entite.Filiale;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FilialeMapper {

    public FilialeDTO toDTO(Filiale filiale) {
        FilialeDTO filialeDTO = new FilialeDTO();
        BeanUtils.copyProperties(filiale, filialeDTO);
        return filialeDTO;
    }
    public static Filiale toEntity(FilialeDTO filialeDTO) {
        Filiale filiale = new Filiale();
        BeanUtils.copyProperties(filialeDTO, filiale);
        return filiale;
    }




}
