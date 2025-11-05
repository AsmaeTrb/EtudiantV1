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
import org.springframework.security.access.prepost.PreAuthorize;
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
                    )
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    @GetMapping
    public ResponseEntity<List<EtudiantResponse>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.getALLEtudiant());
    }

    @Operation(
            summary = "Récupérer un étudiant par son identifiant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Étudiant trouvé",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EtudiantResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Étudiant introuvable")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<EtudiantResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(etudiantService.getEtudiantById(id)); // lève 404 si non trouvé dans le service
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
    // ===== G
    @PreAuthorize("hasAuthority('SCOPE _ADMIN')")
    @PostMapping
    public ResponseEntity<EtudiantResponse> createEtudiant(@RequestBody EtudiantRequest etudiantRequest) {
        EtudiantResponse created = etudiantService.createEtudiant(etudiantRequest);
        return ResponseEntity.created(URI.create("/api/v1/etudiants/" + created.getId())).body(created);
    }
    @Operation(
            summary = " supprimer etudiant par Id",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien supprimer"),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )

    @PreAuthorize("hasAuthority('SCOPE _ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        etudiantService.DeleteByid(id);
        return ResponseEntity.ok().build();
    }

}
