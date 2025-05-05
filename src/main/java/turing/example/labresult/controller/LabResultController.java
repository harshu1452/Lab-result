package turing.example.labresult.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import turing.example.labresult.dto.LabResultDTO;
import turing.example.labresult.entity.LabResult;
import turing.example.labresult.service.LabResultService;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to lab results.
 * Provides RESTful endpoints for CRUD operations and fetching results based on specific criteria.
 */
@RestController
@RequestMapping("/api/labresults") // Base URL for lab result endpoints
public class LabResultController {

    private final LabResultService service;

    /**
     * Constructor-based dependency injection for LabResultService.
     *
     * @param service the LabResultService instance used for business logic
     */
    public LabResultController(LabResultService service) {
        this.service = service;
    }

    /**
     * Retrieves all lab results.
     *
     * @return a list of all lab results in DTO format
     */
    @GetMapping
    public ResponseEntity<List<LabResultDTO>> getAllResults() {
        List<LabResultDTO> results = service.getAllResults();
        return ResponseEntity.ok(results);
    }

    /**
     * Creates a new lab result.
     *
     * @param labResult the lab result data to be added
     * @return the created lab result
     */
    @PostMapping
    public ResponseEntity<LabResult> createLabResult(@RequestBody LabResult labResult) {
        return ResponseEntity.ok(service.createLabResult(labResult));
    }

    /**
     * Retrieves a lab result by its ID.
     *
     * @param id the lab result ID
     * @return the requested lab result or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<LabResultDTO> getLabResultById(@PathVariable Long id) {
        LabResultDTO result = service.getLabResultById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    /**
     * Updates an existing lab result.
     *
     * @param id the ID of the lab result to update
     * @param updatedLabResult the updated lab result data
     * @return the updated lab result or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<LabResult> updateLabResult(@PathVariable Long id, @RequestBody LabResult updatedLabResult) {
        LabResult result = service.updateLabResult(id, updatedLabResult);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a lab result by its ID.
     *
     * @param id the ID of the lab result to delete
     * @return 204 No Content if deletion is successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabResult(@PathVariable Long id) {
        service.deleteLabResult(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all lab results for a specific patient.
     *
     * @param patientName the name of the patient
     * @return a list of lab results for the patient
     */
    @GetMapping("/patient/{patientName}")
    public ResponseEntity<List<LabResultDTO>> getResultsByPatient(@PathVariable String patientName) {
        List<LabResultDTO> results = service.getResultsByPatient(patientName);
        return results.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(results);
    }

    /**
     * Retrieves all lab results assigned to a specific doctor.
     *
     * @param doctorId the ID of the doctor
     * @return a list of lab results assigned to the doctor
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<LabResultDTO>> getResultsForDoctor(@PathVariable Long doctorId) {
        List<LabResultDTO> results = service.getResultsForDoctor(doctorId);
        return results.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(results);
    }
}
