/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto.arq;

import java.util.ArrayList;

/**
 *
 * @author lucasbloise
 */
public class Proceso {

    private static int cantidadDeProcesos = 0;
    private int cantidadRafagas;
    private int tiempoDeLlegada;
    private int nombreProceso;
    private int tiempoRequerido = 0;
    private int tiempoEmpleado = 0;
    private int tiempoBloqueado = 0;
    private ArrayList<Integer> ciclosParaEjecutar = new ArrayList<Integer>();
    private Estado estado = Estado.NUEVO;

    public Proceso(int cantidadRafagas, int tiempoDeLlegada, int nombreProceso, int tiempoRequerido, int tiempoEmpleado,
            int tiempoBloqueado, ArrayList<Integer> ciclosParaEjecutar, Estado estado) {
        this.cantidadRafagas = cantidadRafagas;
        this.tiempoDeLlegada = tiempoDeLlegada;
        this.nombreProceso = nombreProceso;
        this.tiempoRequerido = tiempoRequerido;
        this.tiempoEmpleado = tiempoEmpleado;
        this.tiempoBloqueado = tiempoBloqueado;
        this.ciclosParaEjecutar = ciclosParaEjecutar;
        this.estado = estado;
    }

    public boolean haEntrado(int tiempo) {
        return this.tiempoDeLlegada <= tiempo;
    }

    public int getRafagaActual() {
        return this.ciclosParaEjecutar.get(0);
    }

    public void incrementarTiempoEmpleado() {
        System.out.println("SE INCREMENTO EL TIEMPO");
        this.tiempoEmpleado += 1;
    }

    public static int getCantidadDeProcesos() {
        return cantidadDeProcesos;
    }

    public static void setCantidadDeProcesos(int cantidadDeProcesos) {
        Proceso.cantidadDeProcesos = cantidadDeProcesos;
    }

    public int getCantidadRafagas() {
        return cantidadRafagas;
    }

    public void setCantidadRafagas(int cantidadRafagas) {
        this.cantidadRafagas = cantidadRafagas;
    }

    public int getTiempoDeLlegada() {
        return tiempoDeLlegada;
    }

    public void setTiempoDeLlegada(int tiempoDeLlegada) {
        this.tiempoDeLlegada = tiempoDeLlegada;
    }

    public int getTiempoRequerido() {
        return tiempoRequerido;
    }

    public void setTiempoRequerido(int tiempoRequerido) {
        this.tiempoRequerido = tiempoRequerido;
    }

    public int getTiempoEmpleado() {
        return tiempoEmpleado;
    }

   

    public int getTiempoBloqueado() {
        return tiempoBloqueado;
    }

    public void setTiempoBloqueado(int tiempoBloqueado) {
        this.tiempoBloqueado = tiempoBloqueado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getCantidadCiclos() {
        return cantidadRafagas;
    }

    public void setCantidadCiclos(int CantidadCiclos) {
        this.cantidadRafagas = CantidadCiclos;
    }

    public int getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(int nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public void agregarTiempoProcesamiento(int p) {
        this.ciclosParaEjecutar.add(p);
    }

    public void incrementarTiempo(int p) {
        this.tiempoRequerido += p;
    }

    public void imprimir() {
        System.out.println("Proceso" + this.nombreProceso);
        System.out.println("TiempoDeLlegada" + this.tiempoDeLlegada);
        System.out.println("CantidadRafagas" + this.cantidadRafagas);
    }

}
