package ma.fsr.tp1.cabinetmedical.service;

import lombok.AllArgsConstructor;
import ma.fsr.tp1.cabinetmedical.repository.PatientRepository;
import ma.fsr.tp1.cabinetmedical.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository repo;

    public Patient createPatient(Patient p) {

        if (p.getNom() == null || p.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire.");
        }

        if (p.getTelephone() != null && p.getTelephone().length() < 10) {
            throw new IllegalArgumentException("Telephone invalide");
        }

        return repo.save(p);
    }

    public List<Patient> ListPatients() {
        return repo.findAll();
    }
}