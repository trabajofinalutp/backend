package com.example.licoreriadb.Service;

import com.example.licoreriadb.Model.Egreso;
import com.example.licoreriadb.Repository.EgresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EgresoService {

    @Autowired
    private EgresoRepository egresoRepository;

    public List<Egreso> findAll() {
        return egresoRepository.findAll();
    }

    public Optional<Egreso> findById(Integer id) {
        return egresoRepository.findById(id);
    }

    public Egreso save(Egreso egreso) {
        return egresoRepository.save(egreso);
    }

    public void deleteById(Integer id) {
        egresoRepository.deleteById(id);
    }
}