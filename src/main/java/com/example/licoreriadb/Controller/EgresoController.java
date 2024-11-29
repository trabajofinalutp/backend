package com.example.licoreriadb.Controller;

import com.example.licoreriadb.Model.Egreso;
import com.example.licoreriadb.Service.EgresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/egresos")
public class EgresoController {

    @Autowired
    private EgresoService egresoService;

    @GetMapping
    public List<Egreso> getAllEgresos() {
        return egresoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Egreso> getEgresoById(@PathVariable Integer id) {
        Optional<Egreso> egreso = egresoService.findById(id);
        return egreso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Egreso createEgreso(@RequestBody Egreso egreso) {
        return egresoService.save(egreso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Egreso> updateEgreso(@PathVariable Integer id, @RequestBody Egreso egresoDetails) {
        Optional<Egreso> egreso = egresoService.findById(id);
        if (egreso.isPresent()) {
            Egreso updatedEgreso = egreso.get();
            updatedEgreso.setDescripcion(egresoDetails.getDescripcion());
            updatedEgreso.setMonto(egresoDetails.getMonto());
            updatedEgreso.setFechaEgreso(egresoDetails.getFechaEgreso());
            egresoService.save(updatedEgreso);
            return ResponseEntity.ok(updatedEgreso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEgreso(@PathVariable Integer id) {
        if (egresoService.findById(id).isPresent()) {
            egresoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}