package ar.com.ada.api.noaa.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.repos.MuestraRepo;

@Service
public class MuestraService {

    @Autowired
    BoyaService boyaService;

    @Autowired
    MuestraRepo muestraRepo;

    public Muestra crear(Integer boyaId, Date horarioMuestra, Double latitud, Double longitud,
            String matriculaEmbarcacion, Double altura) {
        Boya boya = boyaService.buscarPorId(boyaId);
        if (boya != null) {

            Muestra muestra = new Muestra();
            muestra.setAlturaMar(altura);
            muestra.setHorario(horarioMuestra);
            muestra.setLatitud(latitud);
            muestra.setLongitud(longitud);
            muestra.setMatricula(matriculaEmbarcacion);
            boya.agregar(muestra);

            boya.setColorLuz(this.verColor(muestra.getAlturaMar()));
            grabarMuestra(muestra);
        
            return boya.getMuestras().get(boya.getMuestras().size() - 1);
        } else {
            return null;
        }

    }

    public String verColor(Double altura) {

        if (altura < -50 || altura > 50) {
            return "AMARILLO";
        } else if (altura < -100 || altura > 100) {
            return "ROJO";
        } else {
            return "VERDE";
        }
    }

    public void grabarMuestra(Muestra muestra) {

        boyaService.actualizar(muestra.getBoya());

    }

    public Muestra buscarPorId(Integer id) {
        Optional<Muestra> optionalMuestra = muestraRepo.findById(id);

        if (optionalMuestra.isPresent())
            return optionalMuestra.get();
        else
            return null;

    }

    public List<Muestra> buscarTodas() {
        return muestraRepo.findAll();
    }

    public void deleteMuestra(Muestra muestra) {
        muestraRepo.delete(muestra);
    }

   

}

