package ar.com.ada.api.noaa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.models.request.MuestraRequest;
import ar.com.ada.api.noaa.models.response.*;

import ar.com.ada.api.noaa.services.MuestraService;

@RestController
public class MuestraController {
    @Autowired
    MuestraService muestraService;

    @PostMapping("/muestras")
    public ResponseEntity<MuestraResponse> crear(@RequestBody MuestraRequest muestra) {

        MuestraResponse muestraCreada = new MuestraResponse();

        Muestra muestraRegistrada = muestraService.crear(muestra.boyaId, muestra.horario, muestra.latitud,
                muestra.longitud, muestra.matriculaEmbarcacion, muestra.alturaMar);

        muestraCreada.id = muestraRegistrada.getId();

        if (muestra.alturaMar < -50 || muestra.alturaMar > 50) {
            muestraCreada.color = "AMARILLO";
        }
        if (muestra.alturaMar < -100 || muestra.alturaMar > 100) {

            muestraCreada.color = "ROJO";

        } else {

            muestraCreada.color = "VERDE";
        }

        return ResponseEntity.ok(muestraCreada);

    }

    // Reseteara el clor de la luz de la boya a “AZUL” a partir de una muesar
    // especifiac
    @DeleteMapping("/muestras/{id}")
    public ResponseEntity<GenericResponse> eliminar(@PathVariable Integer id) {
        GenericResponse respuesta = new GenericResponse();
        muestraService.eliminar(id);

        respuesta.isOk = true;
        respuesta.message = "boya eliminada";

        return ResponseEntity.ok(respuesta);
    }
}
// faltan endpoints !!!! D: