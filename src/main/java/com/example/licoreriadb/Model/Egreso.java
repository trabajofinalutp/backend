package com.example.licoreriadb.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Egreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEgreso;

    private String descripcion;
    private BigDecimal monto;

    @Column(name = "fecha_egreso", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fechaEgreso = LocalDate.now(); // Initialize with current date


}
