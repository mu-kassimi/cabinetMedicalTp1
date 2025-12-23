package ma.fsr.tp1.cabinetmedical.web;

import lombok.AllArgsConstructor;
import ma.fsr.tp1.cabinetmedical.model.Patient;
import ma.fsr.tp1.cabinetmedical.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public Patient create(@RequestBody Patient p) {
        return patientService.createPatient(p);
    }

    @GetMapping
    public List<Patient> getAll() {
        return patientService.ListPatients();
    }
}