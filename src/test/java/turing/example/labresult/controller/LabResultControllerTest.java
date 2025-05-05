package turing.example.labresult.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import turing.example.labresult.dto.LabResultDTO;
import turing.example.labresult.entity.LabResult;
import turing.example.labresult.entity.ResultStatus;
import turing.example.labresult.service.LabResultService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.HttpStatus.*;

import com.fasterxml.jackson.databind.ObjectMapper;

class LabResultControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LabResultService labResultService;

    @InjectMocks
    private LabResultController labResultController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(labResultController).build();
    }

    /**
     * Tests retrieving all lab results.
     */
    @Test
    void testGetAllResults() throws Exception {
        List<LabResultDTO> results = Arrays.asList(new LabResultDTO(1L, "Alice", "Blood Test", ResultStatus.COMPLETED, LocalDateTime.now(), 101L));
        when(labResultService.getAllResults()).thenReturn(results);

        mockMvc.perform(get("/api/labresults"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(results.size()));

        verify(labResultService, times(1)).getAllResults();
    }



    /**
     * Tests deleting a lab result.
     */
    @Test
    void testDeleteLabResult() throws Exception {
        Long id = 1L;
        doNothing().when(labResultService).deleteLabResult(id);

        mockMvc.perform(delete("/api/labresults/{id}", id))
                .andExpect(status().isNoContent());

        verify(labResultService, times(1)).deleteLabResult(id);
    }

    /**
     * Tests retrieving lab results for a specific patient.
     */
    @Test
    void testGetResultsByPatient() throws Exception {
        String patientName = "Alice";
        List<LabResultDTO> results = Arrays.asList(new LabResultDTO(1L, patientName, "MRI", ResultStatus.COMPLETED, LocalDateTime.now(), 101L));
        when(labResultService.getResultsByPatient(patientName)).thenReturn(results);

        mockMvc.perform(get("/api/labresults/patient/{patientName}", patientName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(results.size()));

        verify(labResultService, times(1)).getResultsByPatient(patientName);
    }

    /**
     * Tests retrieving lab results assigned to a specific doctor.
     */
    @Test
    void testGetResultsForDoctor() throws Exception {
        Long doctorId = 101L;
        List<LabResultDTO> results = Arrays.asList(new LabResultDTO(1L, "Alice", "Blood Test", ResultStatus.COMPLETED, LocalDateTime.now(), doctorId));
        when(labResultService.getResultsForDoctor(doctorId)).thenReturn(results);

        mockMvc.perform(get("/api/labresults/doctor/{doctorId}", doctorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(results.size()));

        verify(labResultService, times(1)).getResultsForDoctor(doctorId);
    }
}
