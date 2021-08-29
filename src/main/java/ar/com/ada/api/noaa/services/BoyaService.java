package ar.com.ada.api.noaa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;

import ar.com.ada.api.noaa.repos.BoyaRepo;

@Service
public class BoyaService {
    @Autowired
    BoyaRepo repo;

    public void crearBoya(double longitudInstalacion, double latitudInstalacion) {
        Boya boya = new Boya();
        boya.setLongitudInstalacion(longitudInstalacion);
        boya.setLatitudInstalacion(latitudInstalacion);

        repo.save(boya);
    }

    public List<Boya> traer() {
        return repo.findAll();
    }

    public Boya buscarBoya(Integer boyaId) {
        return repo.findByboyaId(boyaId);
    }

    public void actualizar(Boya boya) {
        repo.save(boya);
    }

}
