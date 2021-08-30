package ar.com.ada.api.noaa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.models.request.BoyaNuevaInfo;
import ar.com.ada.api.noaa.models.request.ColorDeBoya;
import ar.com.ada.api.noaa.models.response.GenericResponse;
import ar.com.ada.api.noaa.services.BoyaService;

@RestController
public class BoyaController {
      /*private BoyaService service;
   public BoyaController(BoyaService service){
       this.service=service;
   }
*/
    @Autowired
    BoyaService boyaService;

    @PostMapping("/api/boyas")
    public ResponseEntity<GenericResponse> nuevaBoya(@RequestBody BoyaNuevaInfo boya) {
        GenericResponse genericResponse = new GenericResponse();
        boyaService.crearBoya(boya.latitudInstalacion, boya.longitudInstalacion);

        genericResponse.message = "Nueva boya creada correctamente";
        genericResponse.isOk = true;

        return ResponseEntity.ok(genericResponse);

    }

    @GetMapping("/api/boyas") // que devuelva las boyas SIN las muestras.

    public ResponseEntity<List<Boya>> traer() {

        return ResponseEntity.ok(boyaService.traer());
    }

    @GetMapping("/api/boyas/{id}") // : que devuelva la info de una boya en particular(SIN las muestras)

    public ResponseEntity<Boya> buscarPorId(@PathVariable Integer id) {
        Boya boya = boyaService.buscarBoya(id);
        return ResponseEntity.ok(boya);
    }

    @PutMapping("/boyas/{id}") // actualiza una categoria existente por color
    public ResponseEntity<GenericResponse> actualizar(@PathVariable Integer id, @RequestBody ColorDeBoya infoColor) {
        GenericResponse respuesta = new GenericResponse();
        Boya boya = boyaService.buscarBoya(id);
        boya.setColorLuz(infoColor.color);
        boyaService.actualizar(boya);

        respuesta.isOk = true;
        respuesta.message = "boyaactualizada";

        return ResponseEntity.ok(respuesta);

    }

}
