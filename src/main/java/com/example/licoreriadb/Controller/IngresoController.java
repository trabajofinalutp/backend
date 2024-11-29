package com.example.licoreriadb.Controller;

import com.example.licoreriadb.Model.Ingreso;
import com.example.licoreriadb.Service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingresos")
public class IngresoController {

    @Autowired
    private IngresoService ingresoService;

    @GetMapping
    public List<Ingreso> getAllIngresos() {
        return ingresoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingreso> getIngresoById(@PathVariable Integer id) {
        Optional<Ingreso> ingreso = ingresoService.findById(id);
        return ingreso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ingreso createIngreso(@RequestBody Ingreso ingreso) {
        return ingresoService.save(ingreso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingreso> updateIngreso(@PathVariable Integer id, @RequestBody Ingreso ingresoDetails) {
        Optional<Ingreso> ingreso = ingresoService.findById(id);
        if (ingreso.isPresent()) {
            Ingreso updatedIngreso = ingreso.get();
            updatedIngreso.setDescripcion(ingresoDetails.getDescripcion());
            updatedIngreso.setMonto(ingresoDetails.getMonto());
            updatedIngreso.setFechaIngreso(ingresoDetails.getFechaIngreso());
            ingresoService.save(updatedIngreso);
            return ResponseEntity.ok(updatedIngreso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngreso(@PathVariable Integer id) {
        if (ingresoService.findById(id).isPresent()) {
            ingresoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}