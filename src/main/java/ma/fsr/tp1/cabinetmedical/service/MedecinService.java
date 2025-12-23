package ma.fsr.tp1.cabinetmedical.service;

import lombok.AllArgsConstructor;
import ma.fsr.tp1.cabinetmedical.model.Medecin;
import ma.fsr.tp1.cabinetmedical.repository.MedecinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedecinService {
    private final MedecinRepository repo;

    public Medecin createMedecin(Medecin m) {
        if (m.getNom() == null || m.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire.");
        }

        return repo.save(m);
    }

    public List<Medecin> listMedecins() { return repo.findAll(); }
}