package ma.fsr.tp1.cabinetmedical.service;

import lombok.AllArgsConstructor;
import ma.fsr.tp1.cabinetmedical.model.Consultation;
import ma.fsr.tp1.cabinetmedical.model.RendezVous;
import ma.fsr.tp1.cabinetmedical.repository.ConsultationRepository;
import ma.fsr.tp1.cabinetmedical.repository.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final RendezVousRepository rendezVousRepository;


    public Consultation saveConsultation(Long rendezVousId, String rapport) {

        Optional<RendezVous> rendezVousOpt = rendezVousRepository.findById(rendezVousId);
        if (rendezVousOpt.isEmpty()) {
            throw new IllegalArgumentException("Rendez-vous introuvable avec l'ID: " + rendezVousId);
        }

        RendezVous rendezVous = rendezVousOpt.get();

        if (consultationRepository.findByRendezVous(rendezVous).isPresent()) {
            throw new IllegalStateException("Une consultation existe déjà pour ce rendez-vous");
        }

        if (rapport == null || rapport.trim().isEmpty()) {
            throw new IllegalArgumentException("Le rapport de consultation est obligatoire");
        }

        Consultation consultation = new Consultation();
        consultation.setDateConsultation(LocalDate.now());
        consultation.setRapport(rapport.trim());
        consultation.setRendezVous(rendezVous);

        rendezVous.setStatut("CONSULTE");
        rendezVousRepository.save(rendezVous);

        return consultationRepository.save(consultation);
    }

}