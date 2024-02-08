package com.example.ofppt.Entite;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="modulePrograme")

public class ModulePrograme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToOne
    @JoinColumn(name="professeur")
    private Professeur professeur;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "moduleprograme_groupe",
            joinColumns = @JoinColumn(name = "moduleprograme_id"),
            inverseJoinColumns = @JoinColumn(name = "groupe_id"))
    private Set<Groupe> groupes;

    @ManyToMany(mappedBy = "moduleProgrames")
    private List<Stagiaire> stagiaires;

   /* @ManyToMany(mappedBy = "moduleProgrames")
    private List<Filiale> filiales;*/
   @ManyToMany
   @JoinTable(name = "moduleprograme_filiale",
           joinColumns = @JoinColumn(name = "moduleprograme_id"),
           inverseJoinColumns = @JoinColumn(name = "filiale_id"))
   private Set<Filiale> filiales;


}
