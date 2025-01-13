package com.taller.contrato.Service;

import com.taller.contrato.Entities.Contrato;

import java.util.List;
import java.util.Optional;

public interface ContratoService {
    //Buscar
    List<Contrato> findAll();

    //Buscar por ID
    Optional<Contrato> findById(int id);

    //Guardar un curso
    Contrato save(Contrato contrato);

    //Eliminar por ID
    void deleteById(int id);
}


