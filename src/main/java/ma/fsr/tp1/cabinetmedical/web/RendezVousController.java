package ma.fsr.tp1.cabinetmedical.web;

import lombok.AllArgsConstructor;
import ma.fsr.tp1.cabinetmedical.model.RendezVous;
import ma.fsr.tp1.cabinetmedical.service.RendezVousService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rendezvous")
@AllArgsConstructor
public class RendezVousController {

    public final RendezVousService rendezVousService;

    @PostMapping
    public RendezVous create(@RequestBody RendezVous rendezVous){ return rendezVousService.createRendezVous(rendezVous); }

    @GetMapping
    public List<RendezVous> getAll(){ return rendezVousService.listRendezVous(); }
}