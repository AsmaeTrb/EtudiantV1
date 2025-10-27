package com.example.etudiantv1.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EtudiantRequest {
    private String nom;
    private  String prenom;
    private String cne;
    private  Long filliereId;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCne() {
        return cne;
    }

    public Long getFilliereId() {
        return filliereId;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public void setFilliereId(Long filliereId) {
        this.filliereId = filliereId;
    }
}

