package com.mycompany.proyecto.arq.Controllers;

import java.util.Scanner;

import com.mycompany.proyecto.arq.Estado;
import com.mycompany.proyecto.arq.Proceso;

public class RutinasController {
    private static int tiempo = -1;
    private static boolean debeContinuar = false;

    private static void nuevoAListo(int tiempoActual) {
        for (Proceso p : ProcesoController.procesosPorEjecutar) {
            if (p.getEstado() == Estado.NUEVO) {
                GraficoController.grafico[5][tiempoActual] = "1P" + p.getNombreProceso();
                p.setEstado(Estado.LISTO);
                debeContinuar = true;
                break;
            }

        }
    }

    private static void listoEjecucion(int tiempoActual) {
        for (Proceso p : ProcesoController.procesosPorEjecutar) {
            if (p.getEstado() == Estado.LISTO) {
                GraficoController.grafico[5][tiempoActual] = "2P" + p.getNombreProceso();
                p.setEstado(Estado.EJECUCCION);
                debeContinuar = true;
                break;
            }

        }
    }

    private static Proceso getProcesosEnEjecucion() {
        for (Proceso p : ProcesoController.procesosPorEjecutar)
            if (p.getEstado() == Estado.EJECUCCION)
                return p;
        return null;
    }

    private static void continuarEjecutando() {
        if (getProcesosEnEjecucion() == null) return;
        getProcesosEnEjecucion().incrementarTiempoEmpleado();
        GraficoController.grafico[getProcesosEnEjecucion().getNombreProceso() + 1][tiempo] = " X ";
        debeContinuar = true;
    }

    private static void grabarTablas(int tiempo) {
        for (Proceso p : ProcesoController.procesosPorEjecutar) {
            switch (p.getEstado()) {
                case LISTO:
                    if (GraficoController.grafico[0][tiempo] == null)
                        GraficoController.grafico[0][tiempo] = "";
                    GraficoController.grafico[0][tiempo] += p.getNombreProceso() + "";
                    break;
                case BLOQUEADO:
                    if (GraficoController.grafico[1][tiempo] == null)
                        GraficoController.grafico[1][tiempo] = "";
                    GraficoController.grafico[1][tiempo] += "" + p.getNombreProceso();
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

        while(!terminarEjecuccion()){
            tiempo++;
            debeContinuar = false;
            grabarTablas(tiempo);
            if (tiempo >= 100) break;
            aumentarTiempoProcesosBloqueado();

            terminarProceso();
            if (debeContinuar) continue;

            bloquearProceso();
            if (debeContinuar) continue;

            continuarEjecutando();
            if (debeContinuar) continue;

            if (procesoNuevo(tiempo)) continue;
            

            desbloquearProceso(tiempo);
            if (debeContinuar)
                continue;
            nuevoAListo(tiempo);
            if (debeContinuar)
                continue;
            listoEjecucion(tiempo);
            if (debeContinuar)
                continue;

        }
        System.out.println();

        GraficoController.imprimirTabla();
        // GraficoController.imprimirTabla2();
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
            } //TODO:algo uele mal
            if (p.getTiempoDeLlegada() == tiempo) {
                p.setEstado(Estado.LISTO);
                GraficoController.grafico[5][tiempo] = "1P" + p.getNombreProceso();
                debeContinuar = true;
                break;
            }
        }
        return debeContinuar;
    }

    private static Proceso getProcesoEnEjecucion() {
        for (Proceso p : ProcesoController.procesosPorEjecutar)
            if (p.getEstado() == Estado.EJECUCCION)
                return p;

        return null;
    }

    private static void bloquearProceso() {
        if (getProcesoEnEjecucion() == null)
            return;
        if (getProcesoEnEjecucion().getTiempoEmpleado() + 1 == getProcesoEnEjecucion().getRafagaActual()) {
            getProcesoEnEjecucion().reiniciarTiempoEjecuccion();
            
            tiempo++;
            GraficoController.grafico[5][tiempo] = "3P" + getProcesoEnEjecucion().getNombreProceso();
            getProcesoEnEjecucion().setEstado(Estado.BLOQUEADO);
            debeContinuar = true;
            return;
        }
    }

    private static void desbloquearProceso(int tiempo) {
        if (getProcesoEnEjecucion() != null)
            return;
        for (Proceso p : ProcesoController.procesosPorEjecutar) {
            if (p.deboDesbloquear() && p.getEstado() == Estado.BLOQUEADO) {
                p.reducirRafagaProcesamiento();
                p.reiniciarTiempoBloqueado();
                System.out.println("ESTOY DESBLOQUEANDO" + tiempo);
                GraficoController.grafico[5][tiempo] = "4P" + p.getNombreProceso();
                p.setEstado(Estado.LISTO);
                debeContinuar = true;
                return;
            }

        }

    }

    private static void aumentarTiempoProcesosBloqueado() {
        for (Proceso p : ProcesoController.procesosPorEjecutar) {
            if (p.getEstado() == Estado.BLOQUEADO) {
                p.incrementarTiempoBloqueado(tiempo);

            }

        }

    }

    private static void terminarProceso() {
        if (getProcesoEnEjecucion() == null) return;

        if (getProcesoEnEjecucion().deboTerminar()) {
            tiempo++;
            GraficoController.grafico[5][tiempo] = "6P" + getProcesoEnEjecucion().getNombreProceso();
            getProcesoEnEjecucion().setEstado(Estado.TERMINADO);
            debeContinuar = true;
            return;
        }

    }

    private static boolean terminarEjecuccion() {
        int aux = 0;
        for (Proceso p : ProcesoController.procesosPorEjecutar) {
            if (p.getEstado() == Estado.TERMINADO) {
                aux += 1;
            }
        }
        return aux == 3;
    }

}
