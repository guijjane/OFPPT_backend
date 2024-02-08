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
@Table(name="filiale")
public class Filiale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToOne
    @JoinColumn(name="professeur")
    private Professeur professeur;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "filiale_moduleprograme",
            joinColumns = @JoinColumn(name = "filiale_id"),
            inverseJoinColumns = @JoinColumn(name = "moduleprograme_id"))
    private Set<ModulePrograme> moduleProgrames;



    @OneToMany(mappedBy = "filiale",cascade = CascadeType.ALL)
    private List<Groupe> groupes ;

    @OneToMany(mappedBy = "filiale")
    private List<Stagiaire> stagiaires;
}
