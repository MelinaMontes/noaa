package ar.com.ada.api.noaa.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.repos.MuestraRepo;
import net.bytebuddy.asm.Advice.Return;

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
        muestra.setAlturaMar(alturaMar);
        muestra.setHorario(horarioMuestra);
        muestra.setLatitud(latitud);
        muestra.setLongitud(longitud);
        muestra.setMatricula(matriculaEmbarcacion);

        boyaService.guardar(boya);
        return muestra;

    }

    public List<Muestra> buscarTodas(Integer boyaId) {
        Boya boya = boyaService.buscarBoya(boyaId);
        return boya.getMuestras();
    }

    public boolean actualizarColorDeLaBoya(Integer muestraId) {
        Muestra muestra = muestraRepo.findByMuestraId(muestraId);
        if (muestra != null) {
            Boya boya = muestra.getBoya();
            boya.setColorLuz("AZUL");// PORQUE ES LA QUE VA X DEFAULT

        }
        return true;

    }

    public Muestra buscarPorMuestraId(Integer muestraId) {
        return muestraRepo.findByMuestraId(muestraId);
    }

    public String muestraColor(Muestra muestra) {

        if (muestra.getAlturaMar() < -50 || muestra.getAlturaMar() > 50) {
            return "AMARILLO";
        }
        if (muestra.getAlturaMar() < -100 || muestra.getAlturaMar() > 100) {
            return "ROJO";
        } else {
            return "VERDE";
        }
    }

}
