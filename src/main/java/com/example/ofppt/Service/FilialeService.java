package com.example.ofppt.Service;

import com.example.ofppt.Dto.FilialeDTO;
import com.example.ofppt.Dto.StagiaireDTO;
import com.example.ofppt.Entite.Filiale;
import com.example.ofppt.Entite.Stagiaire;
import com.example.ofppt.Mapper.FilialeMapper;
import com.example.ofppt.Repository.FilialeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class FilialeService {
    private FilialeRepository filialeRepository;
    private FilialeMapper filialeMapper;



    public List<FilialeDTO> getAllFiliales() {
        List<Filiale> filiales = filialeRepository.findFilialeDTOBy();
        return filiales.stream()
                .map(filialeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FilialeDTO getFilialeById(Long id) {
        Optional<Filiale> filialeOptional = filialeRepository.findFilialeDTOById(id);
        return filialeOptional.map(filialeMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Cette filiale n'existe pas"));
    }

    public List<FilialeDTO> getFilialesBynom(String nom) {
        List<Filiale> filiales = filialeRepository.findFilialeDTOByNom(nom);

        if (filiales.isEmpty()) {
            throw new IllegalArgumentException("Aucune filiale trouvée avec ce nom");
        }

        return filiales.stream()
                .map(filialeMapper::toDTO)
                .collect(Collectors.toList());
    }


    public List<FilialeDTO> getFilialesByNom(String nom) {
        List<Filiale> filiales = filialeRepository.findFilialeDTOByNom(nom);
        return filiales.stream()
                .map(filialeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FilialeDTO createFiliale(FilialeDTO filialeDTO) {
        Filiale filiale = filialeMapper.toEntity(filialeDTO);
        filiale = filialeRepository.save(filiale);
        return filialeMapper.toDTO(filiale);
    }

    public FilialeDTO updateFiliale(Long id, FilialeDTO updateFiliale) {
        Optional<Filiale> filialeOptional = filialeRepository.findFilialeDTOById(id);

        if (filialeOptional.isPresent()) {
            Filiale filiale = filialeOptional.get();
            filiale.setNom(updateFiliale.getNom());
            return filialeMapper.toDTO(filialeRepository.save(filiale));
        } else {
            throw new IllegalArgumentException("Cette filiale n'existe pas");
        }
    }

    public void deleteFiliale(Long id) {
        filialeRepository.deleteById(id);
    }



}
