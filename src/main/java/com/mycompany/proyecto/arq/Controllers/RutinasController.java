package com.mycompany.proyecto.arq.Controllers;

import com.mycompany.Estado;
import com.mycompany.proyecto.arq.Proceso;

public class RutinasController {
    int tiempo = 0;

    public static void ejecutarProcesos() {
        while (quedenProcesos()) {
            
        }
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
