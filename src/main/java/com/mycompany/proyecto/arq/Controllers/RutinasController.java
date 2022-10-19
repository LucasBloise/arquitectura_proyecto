package com.mycompany.proyecto.arq.Controllers;

import java.util.Scanner;

import com.mycompany.proyecto.arq.Estado;
import com.mycompany.proyecto.arq.Proceso;

public class RutinasController {
    private static int tiempo = -1;
    private static boolean debeContinuar = false;

    private static void nuevoAListo(int tiempoActual){
        for(Proceso p : ProcesoController.procesosPorEjecutar){
            if(p.getEstado() == Estado.NUEVO){
                GraficoController.grafico[5][tiempoActual] = "1P" + p.getNombreProceso();
                p.setEstado(Estado.LISTO);
                debeContinuar = true;
                break;
            }

        }
    }



    private static void listoEjecucion(int tiempoActual){
        for(Proceso p : ProcesoController.procesosPorEjecutar){
            if(p.getEstado() == Estado.LISTO){
                GraficoController.grafico[5][tiempoActual] = "2P" + p.getNombreProceso();
                p.setEstado(Estado.EJECUCCION);
                debeContinuar = true;
                break;
            }

        }
    }

    private static Proceso getProcesosEnEjecucion(){
        for(Proceso p : ProcesoController.procesosPorEjecutar)
            if(p.getEstado() == Estado.EJECUCCION)
                return p;
        return null;
    }

    private static void continuarEjecutando(int i){
        if(getProcesosEnEjecucion() == null)return;
        if(getProcesosEnEjecucion() != null)
            getProcesosEnEjecucion().setTiempoEmpleado(1);
        debeContinuar = true;
        GraficoController.grafico [getProcesosEnEjecucion().getNombreProceso() + 1][i] = " X ";
    }

    private static void grabarTablas(int tiempo){
        for (Proceso p : ProcesoController.procesosPorEjecutar) {
            switch(p.getEstado()){
                case LISTO:
                    if(GraficoController.grafico[0][tiempo] == null) GraficoController.grafico[0][tiempo] = "";
                    GraficoController.grafico[0][tiempo] += p.getNombreProceso() + "";
                    break;
                case BLOQUEADO:
                    break;
                case EJECUCCION:
                    GraficoController.grafico[p.getNombreProceso() + 1][tiempo] = " X ";
                    break;
                case NUEVO:
                    break;
                case TERMINADO:
                    break;
                default:
                    break;
            }
        }
    }

    public static void ejecutarProcesos() {

        tiempo = 0;
    
       for(int i = 0; i < 99; i++){
        grabarTablas(i);

            debeContinuar = false;
            if (tiempo >= 100)
            break;
            bloquearProceso(i);
            if(debeContinuar)continue;
            if (procesoNuevo(tiempo)) {
                continue;
            }
            continuarEjecutando(i);
            if(debeContinuar)continue;
            nuevoAListo(i);
            if(debeContinuar) continue;
            listoEjecucion(i);
            if(debeContinuar) continue;
                        
            
       }
       System.out.println(
        GraficoController.grafico
    );
            
        
        GraficoController.imprimirTabla();
        new Scanner(System.in).next();
    }

    public static boolean quedenProcesos() {
        boolean quedanProcesos = true;
        for (Proceso proceso : ProcesoController.procesosPorEjecutar) {
            if (proceso.getEstado() == Estado.TERMINADO) {
                quedanProcesos = false;
                break;
            }
        }
        return quedanProcesos;
    }

    public static boolean procesoNuevo(int tiempo) {
        boolean debeContinuar = false;
        for (Proceso p : ProcesoController.procesosPorEjecutar) {
            if (p.getEstado() != Estado.NUEVO) {
                continue;
            }
            if (p.getTiempoDeLlegada() == tiempo) {
                p.setEstado(Estado.LISTO);
                GraficoController.grafico[5][tiempo] = "1P" + p.getNombreProceso();
                debeContinuar = true;
                break;
            }
        }
        return debeContinuar;
    }

    private static Proceso getProcesoEnEjecucion(){
        for(Proceso p : ProcesoController.procesosPorEjecutar)
            if(p.getEstado() == Estado.EJECUCCION) return p;
        
        return null;
    }

    private static void bloquearProceso(int tiempo){
        if(getProcesoEnEjecucion() == null) return;
        if(getProcesoEnEjecucion().getTiempoEmpleado() +1 == getProcesoEnEjecucion().getRafagaActual()){
            GraficoController.grafico[5][tiempo] = "4P" + getProcesoEnEjecucion().getNombreProceso();
            debeContinuar = true;
            getProcesoEnEjecucion().setEstado(Estado.BLOQUEADO);
            return;
        }
    }

}
