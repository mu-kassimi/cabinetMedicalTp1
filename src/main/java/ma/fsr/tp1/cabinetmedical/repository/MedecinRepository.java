package ma.fsr.tp1.cabinetmedical.repository;
import ma.fsr.tp1.cabinetmedical.model.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
}
