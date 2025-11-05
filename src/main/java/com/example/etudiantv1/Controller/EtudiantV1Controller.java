package com.example.etudiantv1.Controller;

import com.example.etudiantv1.DTO.EtudiantRequest;
import com.example.etudiantv1.DTO.EtudiantResponse;
import com.example.etudiantv1.Services.EtudiantService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Étudiants",
                description = "API REST pour gérer les étudiants (ajout, consultation, mise à jour, suppression)",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8081",
                description = "Serveur local de développement"
        )
)
@RestController
@RequestMapping("/api/v1/etudiants")
public class EtudiantV1Controller {

    private final EtudiantService etudiantService;
    public EtudiantV1Controller (EtudiantService etudiantService){
        this.etudiantService = etudiantService;


    }

    @Operation(
            summary = "Récupérer la liste des étudiants",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des étudiants récupérée avec succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EtudiantResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<List<EtudiantResponse>> getAllEtudiants() {
        List<EtudiantResponse> list = etudiantService.getALLEtudiant();
        return ResponseEntity.ok(list);
    }

    // ✅ POST
    @Operation(
            summary = "Créer un nouvel étudiant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EtudiantRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Étudiant créé avec succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EtudiantResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Requête invalide"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    @PostMapping
    public ResponseEntity<EtudiantResponse> createEtudiant(@RequestBody EtudiantRequest etudiantRequest) {
        EtudiantResponse created = etudiantService.createEtudiant(etudiantRequest);
        return ResponseEntity.created(URI.create("/api/v1/etudiants/" + created.getId())).body(created);
    }

}
