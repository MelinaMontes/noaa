package ar.com.ada.api.noaa.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.repos.MuestraRepo;

@Service
public class MuestraService {
    @Autowired
    MuestraRepo muestraRepo;
    @Autowired
    BoyaService boyaService;

    public Muestra crear(Integer boyaId, Date horarioMuestra, Double latitud, Double longitud,
            String matriculaEmbarcacion, Double alturaMar) {

        Muestra muestra = new Muestra();
        Boya boya = boyaService.buscarBoya(boyaId);
        muestra.setBoya(boya);// setea una muestra a una boya
        muestra.getAlturaMar();
        muestra.setHorario(horarioMuestra);
        muestra.setLatitud(latitud);
        muestra.setLongitud(longitud);
        muestra.setMatricula(matriculaEmbarcacion);

        boyaService.actualizar(boya);
        return muestra;

    }

    public List<Muestra> buscarTodas(Integer boyaId) {
        Boya boya = boyaService.buscarBoya(boyaId);
        return boya.getMuestras();
    }

    public void eliminar(Integer muestraId) {
        Muestra muestra = muestraRepo.findByMuestraId(muestraId);
        Integer boyaId = muestra.getBoya().getBoyaId();
        Boya boya = boyaService.buscarBoya(boyaId);
        boya.setColorLuz("AZUL"); // PORQUE ES LA QUE VA X DEFAULT
        boyaService.actualizar(boya);
    }

}
