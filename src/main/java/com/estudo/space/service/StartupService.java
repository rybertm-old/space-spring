package com.estudo.space.service;

import java.util.Arrays;

import com.estudo.space.domain.Galaxy;
import com.estudo.space.domain.Gravity;
import com.estudo.space.domain.Planet;
import com.estudo.space.domain.Satellite;
import com.estudo.space.domain.Star;
import com.estudo.space.repository.GalaxyRepository;
import com.estudo.space.repository.PlanetRepository;
import com.estudo.space.repository.SatelliteRepository;
import com.estudo.space.repository.StarRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StartupService {
    private final GalaxyRepository glRepo;
    private final PlanetRepository plRepo;
    private final SatelliteRepository satRepo;
    private final StarRepository stRepo;

    public void instantiateDB() {
        Planet pl = new Planet("Terra", 600000000.0);
        Planet pl2 = new Planet("Marte", 900000000.0);
        Galaxy gl = new Galaxy("Via Lactea", 2l);
        Galaxy gl2 = new Galaxy("Via Lactea 2", 2l);
        Satellite sat = satRepo.save(new Satellite("Lua", true));
        Satellite sat2 = satRepo.save(new Satellite("Lua falsa", false));
        Satellite sat3 = satRepo.save(new Satellite("Sat 1", false));
        Satellite sat4 = satRepo.save(new Satellite("Sat 2", false));
        Star st1 = stRepo.save(new Star(1.0));
        Star st2 = stRepo.save(new Star(10.0));
        Star st3 = stRepo.save(new Star(100.0));
        Star st4 = stRepo.save(new Star(1000.0));
        Gravity gr = new Gravity(10.0, 50.0);
        Gravity gr2 = new Gravity(800.0, 310.0);

        // Satisfaz relação One-To-One
        gr.setPlanet(pl);
        gr2.setPlanet(pl2);
        pl.setGravity(gr);
        pl2.setGravity(gr2);

        // Persiste Planet
        plRepo.save(pl);
        plRepo.save(pl2);
        glRepo.save(gl);
        glRepo.save(gl2);

        pl.getGalaxies().addAll(Arrays.asList(gl, gl2));
        pl2.getGalaxies().addAll(Arrays.asList(gl2));

        pl.addSatellite(sat);
        pl.addSatellite(sat2);
        pl.addStar(st1);
        pl.addStar(st3);

        pl2.addSatellite(sat3);
        pl2.addSatellite(sat4);
        pl2.addStar(st2);
        pl2.addStar(st4);

        // Atualiza Planets satisfazendo demais relações
        plRepo.save(pl);
        plRepo.save(pl2);
    }
}
