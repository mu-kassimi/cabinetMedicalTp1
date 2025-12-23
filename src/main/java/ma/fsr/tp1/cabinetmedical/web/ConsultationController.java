
package ma.fsr.tp1.cabinetmedical.web;

import lombok.AllArgsConstructor;
import ma.fsr.tp1.cabinetmedical.model.Consultation;
import ma.fsr.tp1.cabinetmedical.service.ConsultationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consultations")
@AllArgsConstructor
public class ConsultationController {

    public final ConsultationService consultationService;

    @PostMapping("create-from/{rdvId}")
    public Consultation addConsultation(@RequestBody String rapport, @PathVariable("rdvId") String rdvId ){
        return consultationService.saveConsultation(Long.valueOf(rdvId), rapport);
    }
}
