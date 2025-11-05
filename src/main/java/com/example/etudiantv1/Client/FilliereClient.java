package com.example.etudiantv1.Client;

import com.example.etudiantv1.DTO.FilliereDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="filiere-service" ,url="http://localhost:8085/v1/filieres")
public interface FilliereClient {
@GetMapping("/{id}")
FilliereDto getFiliereById(@PathVariable("id") Long id);
}
