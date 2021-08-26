package ar.com.ada.api.noaa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.models.request.BoyaNuevaInfo;
import ar.com.ada.api.noaa.repos.BoyaRepo;

@Service
public class BoyaService {
    @Autowired
    BoyaRepo boyaRepo;

    public List<Boya> traer() {
        return boyaRepo.findAll();
    }

    public void nuevaBoya(Boya boya) {
        boyaRepo.save(boya);
    }

    public void actualizar(Boya boya) {
        boyaRepo.save(boya);
    }

    public Boya buscarPorId(Integer id) {
        Optional<Boya> boya = boyaRepo.findById(id);
        if (boya.isPresent())
            return boya.get();
        return null;
    }

    public void actualizar(Integer id, BoyaNuevaInfo boyaNuevaInfo) {
        Boya boya = this.buscarPorId(id);
        boya.setColorLuz(boyaNuevaInfo.otroColor);

    }

    public void eliminar(Integer id) {
    }

}

