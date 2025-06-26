package com.bianca.mobyMetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bianca.mobyMetas.model.Area;
import com.bianca.mobyMetas.repository.AreaRepository;

@RestController
@RequestMapping("/mobyMetas/v1/areas")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping
    public List<Area> listarTodas() {
        return areaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Area buscarPorId(@PathVariable Long id) {
        return areaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Área não encontrada"));
    }
}