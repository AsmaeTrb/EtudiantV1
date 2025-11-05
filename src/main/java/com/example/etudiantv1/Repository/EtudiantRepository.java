package com.example.etudiantv1.Repository;

import com.example.etudiantv1.Entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository  extends JpaRepository<Etudiant, Integer> {
    Optional<Etudiant> findByCne(String cne);
}
