package com.taller.contrato.Service;

import com.taller.contrato.Entities.Contrato;
import com.taller.contrato.Repositories.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoServiceImpl implements ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    // Obtener todos los contratos
    @Override
    public List<Contrato> findAll() {
        return (List<Contrato>) contratoRepository.findAll();
    }

    // Obtener un contrato por ID
    @Override
    public Optional<Contrato> findById(int id) {
        return contratoRepository.findById(id);
    }

    // Guardar un contrato
    @Override
    public Contrato save(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    // Eliminar un contrato por ID
    @Override
    public void deleteById(int id) {
        contratoRepository.deleteById(id);
    }
}
