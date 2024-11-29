package com.example.licoreriadb.Service;

import com.example.licoreriadb.Model.Ingreso;
import com.example.licoreriadb.Repository.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngresoService {

    @Autowired
    private IngresoRepository ingresoRepository;

    public List<Ingreso> findAll() {
        return ingresoRepository.findAll();
    }

    public Optional<Ingreso> findById(Integer id) {
        return ingresoRepository.findById(id);
    }

    public Ingreso save(Ingreso ingreso) {
        return ingresoRepository.save(ingreso);
    }

    public void deleteById(Integer id) {
        ingresoRepository.deleteById(id);
    }
}