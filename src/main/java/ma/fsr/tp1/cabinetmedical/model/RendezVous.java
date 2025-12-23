package ma.fsr.tp1.cabinetmedical.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateRdv;
    private String statut;


    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
