package com.example.etudiantv1.Entities;

import com.example.etudiantv1.DTO.FilliereDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    private String prenom;

    @Column(nullable = false, unique = true)
    private String cne;

    @Column(nullable = false)
    private Long filliereId;

    @Transient
    private FilliereDto filliere;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getCne() { return cne; }
    public void setCne(String cne) { this.cne = cne; }

    public Long getFilliereId() { return filliereId; }
    public void setFilliereId(Long filliereId) { this.filliereId = filliereId; }

    public FilliereDto getFilliere() { return filliere; }
    public void setFilliere(FilliereDto filliere) { this.filliere = filliere; }
}
