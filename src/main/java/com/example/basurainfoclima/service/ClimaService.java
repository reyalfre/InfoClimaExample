package com.example.basurainfoclima.service;

import com.example.basurainfoclima.model.InfoClima;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClimaService implements IClimaService {
    private static ConcurrentHashMap<String, InfoClima> datosInfoClimaPorCiudad = new ConcurrentHashMap<>();

    @Override
    public void generarCiudadFake() {
        InfoClima ciudadFake = new InfoClima("Lepe", 27.3, 10.2);
        datosInfoClimaPorCiudad.put(ciudadFake.getCiudad(), ciudadFake);
    }

    @Override
    public Collection<InfoClima> climaTodas() {
        return datosInfoClimaPorCiudad.values();
    }

    @Override
    public boolean nueva(InfoClima nuevoRegistro) {
        datosInfoClimaPorCiudad.put(nuevoRegistro.getCiudad(), nuevoRegistro);
        return true;
    }

    @Override
    public InfoClima climaPorCiudad(String nombreCiudad) {
        return datosInfoClimaPorCiudad.get(nombreCiudad);
    }
//Para hacer DELETE
    @Override
    public boolean eliminarPorCiudad(String nombreCiudad) {
        InfoClima removed = datosInfoClimaPorCiudad.remove(nombreCiudad);
        return removed != null;
    }
//Para hacer PUT
    @Override
    public boolean actualizarPorCiudad(String nombreCiudad, InfoClima infoActualizada) {
        if (datosInfoClimaPorCiudad.containsKey(nombreCiudad)) {
            datosInfoClimaPorCiudad.put(nombreCiudad, infoActualizada);
            return true; //Actualización exitosa
        }
        return false; //La ciudad no existe, actualización no exitosa
    }
    //Para hacer PATCH
    @Override
    public boolean actualizarParcialPorCiudad(String nombreCiudad, InfoClima infoClimaParcial) {
        if (datosInfoClimaPorCiudad.containsKey(nombreCiudad)) {
            InfoClima infoClimaActual = datosInfoClimaPorCiudad.get(nombreCiudad);
            if (infoClimaParcial.getTemperatura() != 0) {
                infoClimaActual.setTemperatura(infoClimaParcial.getTemperatura());
            }
            if (infoClimaParcial.getHumedad() != 0) {
                infoClimaActual.setHumedad(infoClimaParcial.getHumedad());
            }
            datosInfoClimaPorCiudad.put(nombreCiudad, infoClimaActual);
            return true;
        }
        return false;
    }
}
