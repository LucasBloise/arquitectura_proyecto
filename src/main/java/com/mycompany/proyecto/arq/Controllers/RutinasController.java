package com.mycompany.proyecto.arq.Controllers;

import java.util.Scanner;

import com.mycompany.proyecto.arq.Estado;
import com.mycompany.proyecto.arq.Proceso;

public class RutinasController {
    private static int tiempo = -1;
    private static int i = 0;
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

    private static void continuarEjecutando(int i) {
        if (getProcesosEnEjecucion() == null)
            return;
        getProcesosEnEjecucion().incrementarTiempoEmpleado();
        System.out.println(getProcesoEnEjecucion().getTiempoEmpleado());
        debeContinuar = true;
        GraficoController.grafico[getProcesosEnEjecucion().getNombreProceso() + 1][i] = " X ";
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

        tiempo = 0;

        for (i = 0; i < 99; i++) {
            grabarTablas(i);

            debeContinuar = false;
            if (tiempo >= 100)
                break;
            aumentarTiempoProcesosBloqueado();
            bloquearProceso(i);
            if (debeContinuar)
                continue;
            if (procesoNuevo(tiempo)) {
                continue;
            }

            continuarEjecutando(i);
            if (debeContinuar)
                continue;
            desbloquearProceso(i);
            if (debeContinuar)
                continue;
            nuevoAListo(i);
            if (debeContinuar)
                continue;
            listoEjecucion(i);
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

    private static Proceso getProcesoEnEjecucion() {
        for (Proceso p : ProcesoController.procesosPorEjecutar)
            if (p.getEstado() == Estado.EJECUCCION)
                return p;

        return null;
    }

    private static void bloquearProceso(int tiempo) {
        if (getProcesoEnEjecucion() == null)
            return;
        if (getProcesoEnEjecucion().getTiempoEmpleado() + 1 == getProcesoEnEjecucion().getRafagaActual()) {
            i++;
            GraficoController.grafico[5][i] = "3P" + getProcesoEnEjecucion().getNombreProceso();
            debeContinuar = true;
            getProcesoEnEjecucion().reiniciarTiempoEjecuccion();
            getProcesoEnEjecucion().setEstado(Estado.BLOQUEADO);
            return;
        }
    }

    private static void desbloquearProceso(int tiempo) {
        if (getProcesoEnEjecucion() != null)
            return;
        for (Proceso p : ProcesoController.procesosPorEjecutar) {
            if (p.deboDesbloquear() && p.getEstado() == Estado.BLOQUEADO) {
                i++;
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

}
