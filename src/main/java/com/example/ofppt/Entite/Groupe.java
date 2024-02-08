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
@Table(name="groupe")
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;


    @ManyToOne
    @JoinColumn(name = "filiale_id")
    private Filiale filiale;

    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
    private List<Stagiaire> stagiaires;

    @ManyToMany(mappedBy = "groupes")
    private Set<ModulePrograme> moduleProgrames;
}
