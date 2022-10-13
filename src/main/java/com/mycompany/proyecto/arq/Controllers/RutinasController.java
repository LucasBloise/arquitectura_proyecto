package com.mycompany.proyecto.arq.Controllers;

import com.mycompany.Estado;
import com.mycompany.proyecto.arq.Proceso;

public class RutinasController {
    private static int tiempo = 0;

    public static void ejecutarProcesos() {
        boolean continuar = false;
        while (quedenProcesos()) {
            if (tiempo >= 100)
                break;
            tiempo += 1;
            for (Proceso proceso : ProcesoController.procesosPorEjecutar) {
                if (proceso.getEstado() == Estado.NUEVO) {
                    // grabar en table, le cambio el estado y continue
                    if (proceso.getTiempoDeLlegada() >= tiempo) {
                        continue;
                    }
                    GraficoController.grafico[6][tiempo] = "1P" + proceso.getNombreProceso();
                    proceso.setEstado(Estado.LISTO);
                    continuar = true;
                }
                if (continuar) {
                    continue;
                }
            }

        }
        GraficoController.imprimirTabla();
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

}