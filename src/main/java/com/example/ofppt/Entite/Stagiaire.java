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
@Table(name="stagiaire")
public class Stagiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private Long age;
    private String cin;
    private String telephone;
    private String email;
    private String  adress;
    private String  diplome;
    private String  fonction;

    @ManyToOne
    @JoinColumn(name = "filiale_id")
    private Filiale filiale;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "stagiaire_moduleprograme",
            joinColumns = @JoinColumn(name = "stagiaire_id"),
            inverseJoinColumns = @JoinColumn(name = "moduleprograme_id"))
    private Set<ModulePrograme> moduleProgrames;

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;

    @ManyToMany(mappedBy = "stagiaires")
    private List<Professeur> professeurs;

    private boolean hasDocuments;
    private String base64File;

    public String getBase64File() {
        return base64File;
    }

    public void setBase64File(String base64File) {
        this.base64File = base64File;
    }

    public Stagiaire( Set<ModulePrograme> moduleProgrames) {
        this.moduleProgrames = moduleProgrames;
    }


}
