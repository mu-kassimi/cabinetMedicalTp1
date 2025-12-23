package ma.fsr.tp1.cabinetmedical.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateConsultation;
    private String rapport;
    @ManyToOne
    @JoinColumn(name = "rendez_vous_id")
    private RendezVous rendezVous;
}
