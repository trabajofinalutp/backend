package com.example.licoreriadb.Controller;

import com.example.licoreriadb.Model.DetalleVenta;
import com.example.licoreriadb.Service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/api/detalleventas")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public List<DetalleVenta> getAllDetalleVentas() {
        return detalleVentaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> getDetalleVentaById(@PathVariable Integer id) {
        Optional<DetalleVenta> detalleVenta = detalleVentaService.findById(id);
        return detalleVenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleVenta createDetalleVenta(@RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.save(detalleVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> updateDetalleVenta(@PathVariable Integer id, @RequestBody DetalleVenta detalleVentaDetails) {
        Optional<DetalleVenta> detalleVenta = detalleVentaService.findById(id);
        if (detalleVenta.isPresent()) {
            DetalleVenta updatedDetalleVenta = detalleVenta.get();
            updatedDetalleVenta.setVenta(detalleVentaDetails.getVenta());
            updatedDetalleVenta.setProducto(detalleVentaDetails.getProducto());
            updatedDetalleVenta.setCantidad(detalleVentaDetails.getCantidad());
            updatedDetalleVenta.setPrecioUnitario(detalleVentaDetails.getPrecioUnitario());
            updatedDetalleVenta.setSubtotal(detalleVentaDetails.getSubtotal());
            detalleVentaService.save(updatedDetalleVenta);
            return ResponseEntity.ok(updatedDetalleVenta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleVenta(@PathVariable Integer id) {
        if (detalleVentaService.findById(id).isPresent()) {
            detalleVentaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}