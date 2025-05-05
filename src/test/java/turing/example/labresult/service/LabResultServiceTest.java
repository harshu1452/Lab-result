package turing.example.labresult.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import turing.example.labresult.dto.LabResultDTO;
import turing.example.labresult.entity.LabResult;
import turing.example.labresult.entity.ResultStatus;
import turing.example.labresult.repository.LabResultRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for LabResultService.
 * Uses Mockito to mock dependencies and ensure proper behavior of service methods.
 */
@ExtendWith(MockitoExtension.class)
class LabResultServiceTest {

    @Mock
    private LabResultRepository repository; // Mocked repository to simulate database operations

    @InjectMocks
    private LabResultService service; // Service under test

    private LabResult labResult;
    private LabResult updatedLabResult;
    private LabResultDTO labResultDTO;
    private LocalDateTime now;

    /**
     * Setup method to initialize test data before each test case.
     */
    @BeforeEach
    void setUp() {
        now = LocalDateTime.now(); // Capture the current timestamp

        // Sample lab result entity
        labResult = new LabResult(1L, "Alice", "Blood Test", ResultStatus.PENDING, now, 101L);

        // Sample updated lab result with modified details
        updatedLabResult = new LabResult(1L, "Alice", "MRI Scan", ResultStatus.COMPLETED, now, 101L);

        // Sample DTO representing lab result
        labResultDTO = new LabResultDTO("Alice", "Blood Test", ResultStatus.PENDING.name(), now);
    }

    /**
     * Test case to verify retrieval of all lab results.
     */
    @Test
    void testGetAllResults() {
        when(repository.findAll()).thenReturn(List.of(labResult));

        List<LabResultDTO> results = service.getAllResults();

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals("Alice", results.get(0).getPatientName());

        verify(repository, times(1)).findAll();
    }

    /**
     * Test case to verify retrieving a lab result by ID when it exists.
     */
    @Test
    void testGetLabResultById_Found() {
        when(repository.findById(1L)).thenReturn(Optional.of(labResult));

        LabResultDTO result = service.getLabResultById(1L);

        assertNotNull(result);
        assertEquals("Alice", result.getPatientName());
        assertEquals("Blood Test", result.getTestName());

        verify(repository, times(1)).findById(1L);
    }

    /**
     * Test case for retrieving a lab result by ID when it does not exist.
     */
    @Test
    void testGetLabResultById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        LabResultDTO result = service.getLabResultById(1L);

        assertNull(result);

        verify(repository, times(1)).findById(1L);
    }

    /**
     * Test case to verify creation of a new lab result.
     */
    @Test
    void testCreateLabResult() {
        when(repository.save(labResult)).thenReturn(labResult);

        LabResult savedResult = service.createLabResult(labResult);

        assertNotNull(savedResult);
        assertEquals("Alice", savedResult.getPatientName());

        verify(repository, times(1)).save(labResult);
    }

    /**
     * Test case to verify updating an existing lab result when found.
     */
    @Test
    void testUpdateLabResult_Found() {
        when(repository.findById(1L)).thenReturn(Optional.of(labResult));
        when(repository.save(any(LabResult.class))).thenReturn(updatedLabResult);

        LabResult result = service.updateLabResult(1L, updatedLabResult);

        assertNotNull(result);
        assertEquals("MRI Scan", result.getTestName());
        assertEquals(ResultStatus.COMPLETED, result.getStatus());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(LabResult.class));
    }

    /**
     * Test case for attempting to update a lab result when it does not exist.
     */
    @Test
    void testUpdateLabResult_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        LabResult result = service.updateLabResult(1L, updatedLabResult);

        assertNull(result);

        verify(repository, times(1)).findById(1L);
        verify(repository, never()).save(any(LabResult.class));
    }

    /**
     * Test case to verify deletion of a lab result by ID.
     */
    @Test
    void testDeleteLabResult() {
        doNothing().when(repository).deleteById(1L);

        service.deleteLabResult(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    /**
     * Test case to retrieve all lab results for a specific patient.
     */
    @Test
    void testGetResultsByPatient_Found() {
        when(repository.findAll()).thenReturn(List.of(labResult));

        List<LabResultDTO> results = service.getResultsByPatient("Alice");

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals("Alice", results.get(0).getPatientName());

        verify(repository, times(1)).findAll();
    }

    /**
     * Test case when no lab results exist for a specific patient.
     */
    @Test
    void testGetResultsByPatient_NotFound() {
        when(repository.findAll()).thenReturn(List.of());

        List<LabResultDTO> results = service.getResultsByPatient("Bob");

        assertTrue(results.isEmpty());

        verify(repository, times(1)).findAll();
    }

    /**
     * Test case to retrieve all lab results assigned to a specific doctor.
     */
    @Test
    void testGetResultsForDoctor_Found() {
        when(repository.findAll()).thenReturn(List.of(labResult));

        List<LabResultDTO> results = service.getResultsForDoctor(101L);

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals(101L, labResult.getDoctorId());

        verify(repository, times(1)).findAll();
    }

    /**
     * Test case when no lab results exist for a specific doctor.
     */
    @Test
    void testGetResultsForDoctor_NotFound() {
        when(repository.findAll()).thenReturn(List.of());

        List<LabResultDTO> results = service.getResultsForDoctor(999L);

        assertTrue(results.isEmpty());

        verify(repository, times(1)).findAll();
    }
}
