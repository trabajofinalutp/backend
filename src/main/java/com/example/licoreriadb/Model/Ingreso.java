package com.example.licoreriadb.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngreso;

    private String descripcion;
    private BigDecimal monto;

    @Column(name = "fecha_ingreso", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fechaIngreso = LocalDate.now(); // Initialize with current date

}
