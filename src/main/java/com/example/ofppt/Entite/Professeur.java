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
@Table(name="professeur")
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private Long age;
    private String telephone;
    private String email;
    private String  adress;
    private String specialite;

    @OneToMany(mappedBy = "professeur")
    private List<Filiale> filiales;

    @OneToMany(mappedBy = "professeur")
    private List<ModulePrograme> moduleProgrames ;

    @OneToMany(mappedBy = "professeur")
    private List<Groupe> groupes ;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "professeur_stagiaire",
            joinColumns = @JoinColumn(name = "professeur_id"),
            inverseJoinColumns = @JoinColumn(name = "stagiaire_id"))
    private Set<Stagiaire> stagiaires;

    public void setFiliales(Set<Filiale> filiales) {
        this.filiales = List.copyOf(filiales);
    }

    public void setModuleProgrames(Set<ModulePrograme> moduleProgrames) {
        this.moduleProgrames = List.copyOf(moduleProgrames);
    }
}
