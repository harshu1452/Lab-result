package turing.example.labresult.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turing.example.labresult.dto.LabResultDTO;
import turing.example.labresult.entity.LabResult;
import turing.example.labresult.repository.LabResultRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for handling business logic related to lab results.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class LabResultService {

    private final LabResultRepository repository;

    /**
     * Retrieves all lab results from the database and converts them to DTO format.
     *
     * @return List of LabResultDTO containing lab result details.
     */
    public List<LabResultDTO> getAllResults() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a lab result by its ID.
     *
     * @param id the ID of the lab result
     * @return LabResultDTO if found, otherwise null
     */
    public LabResultDTO getLabResultById(Long id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    /**
     * Creates a new lab result entry.
     *
     * @param labResult the lab result entity to save
     * @return the saved lab result
     */
    public LabResult createLabResult(LabResult labResult) {
        return repository.save(labResult);
    }

    /**
     * Updates an existing lab result.
     *
     * @param id the ID of the lab result to update
     * @param updatedLabResult the updated lab result data
     * @return the updated LabResult entity if found, otherwise null
     */
    public LabResult updateLabResult(Long id, LabResult updatedLabResult) {
        Optional<LabResult> existingLabResult = repository.findById(id);
        if (existingLabResult.isPresent()) {
            LabResult labResult = existingLabResult.get();
            labResult.setPatientName(updatedLabResult.getPatientName());
            labResult.setTestName(updatedLabResult.getTestName());
            labResult.setStatus(updatedLabResult.getStatus());
            labResult.setCreatedAt(updatedLabResult.getCreatedAt());
            return repository.save(labResult);
        }
        return null;
    }

    /**
     * Deletes a lab result by its ID.
     *
     * @param id the ID of the lab result to delete
     */
    public void deleteLabResult(Long id) {
        repository.deleteById(id);
    }

    /**
     * Retrieves all lab results for a specific patient.
     *
     * @param patientName the name of the patient
     * @return List of LabResultDTO for the given patient
     */
    public List<LabResultDTO> getResultsByPatient(String patientName) {
        return repository.findAll().stream()
                .filter(labResult -> labResult.getPatientName().equalsIgnoreCase(patientName))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all lab results for a specific doctor.
     *
     * @param doctorId the ID of the doctor
     * @return List of LabResultDTO for the given doctor
     */
    public List<LabResultDTO> getResultsForDoctor(Long doctorId) {
        return repository.findAll().stream()
                .filter(labResult -> labResult.getDoctorId().equals(doctorId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts a LabResult entity to a LabResultDTO.
     *
     * @param result the LabResult entity
     * @return LabResultDTO representation of the entity
     */
    private LabResultDTO convertToDTO(LabResult result) {
        return new LabResultDTO(
                result.getPatientName(),
                result.getTestName(),
                result.getStatus().name(),
                result.getCreatedAt()
        );
    }
}
