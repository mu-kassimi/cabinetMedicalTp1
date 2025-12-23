package ma.fsr.tp1.cabinetmedical.service;

import lombok.AllArgsConstructor;
import ma.fsr.tp1.cabinetmedical.model.RendezVous;
import ma.fsr.tp1.cabinetmedical.repository.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RendezVousService {

    private final RendezVousRepository repo;

    public RendezVous createRendezVous(RendezVous rdv) {
        return repo.save(rdv);
    }

    public List<RendezVous> listRendezVous() { return repo.findAll(); }
}