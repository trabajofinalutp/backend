package com.example.licoreriadb.Repository;

import com.example.licoreriadb.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
