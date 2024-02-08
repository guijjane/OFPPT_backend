package com.example.ofppt.Service;

import com.example.ofppt.Dto.GroupeDTO;
import com.example.ofppt.Entite.Groupe;
import com.example.ofppt.Mapper.GroupeMapper;
import com.example.ofppt.Repository.GroupeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupeService {
    private GroupeRepository groupeRepository;
    private GroupeMapper groupeMapper;


    public List<GroupeDTO> getAllGroupes() {
        List<Groupe> groupes = groupeRepository.findGroupeDTOBy();
        return groupes.stream()
                .map(groupeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public GroupeDTO getGroupeById(Long id) {
        Optional<Groupe> groupeOptional = groupeRepository.findGroupeDTOById(id);
        return groupeOptional.map(groupeMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Ce groupe n'existe pas"));
    }

    public List<GroupeDTO> getGroupesByNom(String nom) {
        List<Groupe> groupes = groupeRepository.findGroupeDTOByNom(nom);
        return groupes.stream()
                .map(groupeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public GroupeDTO createGroupe(GroupeDTO groupeDTO) {
        Groupe groupe = groupeMapper.toEntity(groupeDTO);
        groupe = groupeRepository.save(groupe);
        return groupeMapper.toDTO(groupe);
    }public GroupeDTO updateGroupe(Long id, GroupeDTO updateGroupe) {
        Optional<Groupe> groupeOptional = groupeRepository.findById(id);

        if (groupeOptional.isPresent()) {
            Groupe groupe = groupeOptional.get();
            groupe.setNom(updateGroupe.getNom());
            return groupeMapper.toDTO(groupeRepository.save(groupe));
        } else {
            throw new IllegalArgumentException("Ce groupe n'existe pas");
        }
    }

    public void deleteGroupe(Long id) {
        groupeRepository.deleteById(id);
    }
}
