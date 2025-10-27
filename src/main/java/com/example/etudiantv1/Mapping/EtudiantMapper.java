package com.example.etudiantv1.Mapping;

import com.example.etudiantv1.DTO.EtudiantRequest;
import com.example.etudiantv1.DTO.EtudiantResponse;
import com.example.etudiantv1.Entities.Etudiant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {

    public Etudiant dtoToEntity(EtudiantRequest etudiantRequest) {
        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(etudiantRequest, etudiant);
        return etudiant;
    }
    public EtudiantResponse entityToDto ( Etudiant etudiant) {
        EtudiantResponse etudiantResponse = new EtudiantResponse();
        BeanUtils.copyProperties(etudiant, etudiantResponse);
        return etudiantResponse;
    }
}
