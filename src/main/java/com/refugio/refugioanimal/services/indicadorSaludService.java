package com.refugio.refugioanimal.services;

import com.refugio.refugioanimal.model.IndicadorSalud;
import com.refugio.refugioanimal.repository.IndicadorSaludRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class indicadorSaludService {

    @Autowired
    private IndicadorSaludRepository indicadorSaludRepository;

    public IndicadorSalud crearIndicadorSalud(IndicadorSalud indicadorSalud)
    {
        return indicadorSaludRepository.save(indicadorSalud);
    }

}
