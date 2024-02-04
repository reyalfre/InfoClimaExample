package com.example.basurainfoclima.service;

import com.example.basurainfoclima.model.InfoClima;

import java.util.Collection;

public interface IClimaService {
    public void generarCiudadFake();
    public Collection<InfoClima> climaTodas();
    public boolean nueva(InfoClima nuevoRegistro);
    public InfoClima climaPorCiudad(String nombreCiudad);
    public boolean eliminarPorCiudad(String nombreCiudad);
    public boolean actualizarPorCiudad(String nombreCiudad, InfoClima infoActualizada);
    //path
    public boolean actualizarParcialPorCiudad(String nombreCiudad, InfoClima infoClimaParcial);
}
