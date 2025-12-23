package ma.fsr.tp1.cabinetmedical.repository;

import ma.fsr.tp1.cabinetmedical.model.Consultation;
import ma.fsr.tp1.cabinetmedical.model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Optional<Object> findByRendezVous(RendezVous rendezVous);
}
