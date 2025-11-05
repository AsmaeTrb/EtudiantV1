package com.example.etudiantv1.Services;

import com.example.etudiantv1.Client.FilliereClient;
import com.example.etudiantv1.DTO.EtudiantRequest;
import com.example.etudiantv1.DTO.EtudiantResponse;
import com.example.etudiantv1.DTO.FilliereDto;
import com.example.etudiantv1.Entities.Etudiant;
import com.example.etudiantv1.Mapping.EtudiantMapper;
import com.example.etudiantv1.Repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EtudiantService {
   private final  EtudiantRepository  etudiantRepository;
   private final  EtudiantMapper etudiantMapper;
   private final FilliereClient filliereClient;
    public EtudiantService(EtudiantRepository etudiantRepository,
                           EtudiantMapper etudiantMapper,
                           FilliereClient filliereClient) {
        this.etudiantRepository = etudiantRepository;
        this.etudiantMapper = etudiantMapper;
        this.filliereClient = filliereClient;
    }


    public EtudiantResponse createEtudiant (EtudiantRequest etudiantRequest) {
        Etudiant etudiant = etudiantMapper.dtoToEntity(etudiantRequest);
        Etudiant saved =etudiantRepository.save(etudiant);
        FilliereDto filliere = filliereClient.getFiliereById(etudiantRequest.getFilliereId());
        EtudiantResponse response = etudiantMapper.entityToDto(saved);
        response.setFilliere(filliere);
        return response;
    }
    public List<EtudiantResponse> getALLEtudiant() {
        return etudiantRepository.findAll().stream().map(etudiantMapper::entityToDto).collect(Collectors.toList());

    }
    public EtudiantResponse getEtudiantById(Integer id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec ID : " + id));

        EtudiantResponse response = etudiantMapper.entityToDto(etudiant);

        FilliereDto filliere = filliereClient.getFiliereById(etudiant.getFilliereId());
        response.setFilliere(filliere);

        return response;
    }
    public void DeleteByid(Integer id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec ID : " + id));

        etudiantRepository.delete(etudiant);
    }



}
