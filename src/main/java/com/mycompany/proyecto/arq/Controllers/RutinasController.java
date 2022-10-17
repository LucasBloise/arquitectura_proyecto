package com.mycompany.proyecto.arq.Controllers;

import com.mycompany.proyecto.arq.Estado;
import com.mycompany.proyecto.arq.Proceso;

public class RutinasController {
    private static int tiempo = -1;

    public static void ejecutarProcesos() {

        for (Proceso p : ProcesoController.procesosPorEjecutar) {
            p.imprimir();
        }

        while (quedenProcesos()) {

            tiempo += 1;
            if (tiempo >= 100)
                break;
            if (procesoNuevo(tiempo)) {
                continue;
            }
        }

        GraficoController.imprimirTabla2();
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

}