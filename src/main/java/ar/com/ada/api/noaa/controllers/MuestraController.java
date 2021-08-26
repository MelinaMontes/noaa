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
import ar.com.ada.api.noaa.models.response.GenericResponse;
import ar.com.ada.api.noaa.models.response.MuestraResponse;
import ar.com.ada.api.noaa.services.BoyaService;
import ar.com.ada.api.noaa.services.MuestraService;

@RestController
public class MuestraController {
    @Autowired
    MuestraService muestraService;
    @Autowired
    BoyaService boyaService;

    @PostMapping("/muestras")
    public ResponseEntity<?> crear(@RequestBody MuestraRequest muestraRequest) {
        MuestraRequest genericResponse = new MuestraRequest();
        MuestraResponse muestraCreada = new MuestraResponse();
        Muestra muestraRegistrada = muestraService.crear();
        return ResponseEntity.ok(muestraCreada);

    }

    @DeleteMapping("/boyas/{id}")
    public ResponseEntity<MuestraRequest> eliminar(@PathVariable Integer id) {

        boyaService.eliminar(id);
        MuestraRequest respuesta = new MuestraRequest();

        respuesta.isOk = true;
        respuesta.message = "boya eliminada";

        return ResponseEntity.ok(respuesta);
    }
}
 //faltan endpoints !!!! D: