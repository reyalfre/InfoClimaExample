package com.example.basurainfoclima.controller;

import com.example.basurainfoclima.model.InfoClima;
import com.example.basurainfoclima.service.IClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/ejemplo-clima")
public class ApiEjemploClima {
    @Autowired
    public IClimaService climaService;
    //http://localhost:8080/api/ejemplo-clima/saludo?nombre=Alfredo&apellido=Maldonado%20Pertuz
    @GetMapping("/saludo")
    public String saludo(@RequestParam(value = "nombre", defaultValue = "(NADIE)") String nombreUsuario, @RequestParam(value = "apellido", defaultValue = "") String apellido) {
        climaService.generarCiudadFake();
        return "Hola " + nombreUsuario + " " + apellido + ", pero qué guapo/a eres";
    }
    //http://localhost:8080/api/ejemplo-clima/nueva-ciudad
    @PostMapping("/nueva-ciudad")
    public ResponseEntity<Boolean> nuevaInformacionClima(@RequestBody InfoClima info) {
        climaService.nueva(info);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/ejemplo-clima/todas
    @GetMapping("/todas")
    public ResponseEntity<Collection<InfoClima>> todasCiudades() {
        Collection<InfoClima> climas = climaService.climaTodas();

        if (climas.isEmpty()) {
            return new ResponseEntity<>(climas, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(climas, HttpStatus.OK);
        }
    }
    //http://localhost:8080/api/by-ciudad/Madrid
    @GetMapping("/by-ciudad/{nombre}")
    public ResponseEntity<InfoClima> getByCiudad(@PathVariable String nombre){
        InfoClima clima = climaService.climaPorCiudad(nombre);
        if (clima == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //TODO: comprobar si es nulo o no, y según eso devolver un 204
        return new ResponseEntity<>(clima, HttpStatus.OK);
    }
    //http://localhost:8080/api/ejemplo-clima/eliminar-ciudad/Madrid
    @DeleteMapping("/eliminar-ciudad/{nombre}")
    public ResponseEntity<Void> eliminarPorCiudad(@PathVariable String nombre) {
        boolean eliminado = climaService.eliminarPorCiudad(nombre);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //http://localhost:8080/api/ejemplo-clima/actualizar-ciudad/Madrid
    @PutMapping("/actualizar-ciudad/{nombre}")
    public ResponseEntity<Void> actualizarCiudad(@PathVariable String nombre, @RequestBody InfoClima infoClima){
        boolean actualizacionExitosa = climaService.actualizarPorCiudad(nombre, infoClima);
        if (actualizacionExitosa){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //http://localhost:8080/api/ejemplo-clima/actualizar-parcial-ciudad/Madrid
    @PatchMapping("/actualizar-parcial-ciudad/{nombre}")
    public ResponseEntity<Void> actualizarParcialCiudad(@PathVariable String nombre, @RequestBody InfoClima infoClimaParcial){
        boolean actualizacionExitosa = climaService.actualizarParcialPorCiudad(nombre, infoClimaParcial);
        if (actualizacionExitosa){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);// 204 No content
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

}
