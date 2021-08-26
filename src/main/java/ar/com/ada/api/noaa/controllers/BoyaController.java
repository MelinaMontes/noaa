package ar.com.ada.api.noaa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.models.request.BoyaNuevaInfo;
import ar.com.ada.api.noaa.models.response.GenericResponse;
import ar.com.ada.api.noaa.services.BoyaService;

@RestController
public class BoyaController {
    @Autowired
    BoyaService boyaService;

    @PostMapping("/boyas")
    public ResponseEntity<GenericResponse> nuevaBoya(@RequestBody Boya boya) {
        boyaService.nuevaBoya(boya);

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.message = "Nueva boya creada correctamente";
        genericResponse.isOk = true;
        genericResponse.id = boya.getId();
        return ResponseEntity.ok(genericResponse);

    }

    @GetMapping("/boyas")
    public ResponseEntity<List<Boya>> traer() {
        List<Boya> mostrarBoyas = boyaService.traer();
        return ResponseEntity.ok(mostrarBoyas);
    }

    @GetMapping("/boyas/{id}")
    public ResponseEntity<Boya> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(boyaService.buscarPorId(id));
    }

    @PutMapping("/boyas/{id}") //actualiza una categoria existente
    public ResponseEntity<GenericResponse> actualizar (@PathVariable Integer id, @RequestBody BoyaNuevaInfo boyaNuevaInfo){
    
        boyaService.actualizar(id, boyaNuevaInfo);
        GenericResponse respuesta = new GenericResponse();

        respuesta.isOk = true;
        respuesta.message = "boyaactualizada";

        return ResponseEntity.ok(respuesta);

    }

    @


}
