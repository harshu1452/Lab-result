package turing.example.labresult.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import turing.example.labresult.entity.LabResult;

import java.util.List;

/**
 * Repository interface for performing database operations on LabResult entities.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface LabResultRepository extends JpaRepository<LabResult, Long> {

    /**
     * Finds all lab results by the patient's name (case-insensitive).
     *
     * @param patientName the patient's name
     * @return List of LabResult entities matching the patient name
     */
    List<LabResult> findByPatientNameIgnoreCase(String patientName);

    /**
     * Finds all lab results associated with a specific doctor ID.
     *
     * @param doctorId the doctor's ID
     * @return List of LabResult entities assigned to the doctor
     */
    List<LabResult> findByDoctorId(Long doctorId);
}
