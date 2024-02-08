package com.example.ofppt.Entite;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="module")

public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToOne
    @JoinColumn(name="professeur_id")
    private Professeur professeur;

    @ManyToOne
    @JoinColumn(name ="filiale_id")
    private Filiale filiale;

    @ManyToMany(mappedBy = "modules")
    private Set<Groupe> groupes;

    @ManyToMany(mappedBy = "modules")
    private Set<Stagiaire> stagiaires;
}
