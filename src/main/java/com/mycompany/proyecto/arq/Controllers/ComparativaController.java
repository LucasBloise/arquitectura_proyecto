package com.mycompany.proyecto.arq.Controllers;

public class ComparativaController {
    public static boolean imprimirGrafica = true;
    public static int tiempoJSF = 0;
    public static int tiempoJSFD = 0;

    public static void mostrarComparativa() {
        RutinasController.ejecutarProcesos(false);
        System.out.println("Tiempo con JSF: " + tiempoJSF);
        limpiarMemoria();
        RutinasController.ejecutarProcesos(true);
        System.out.println("Tiempo con JSFD: " + tiempoJSFD);
    }

    public static void limpiarMemoria() {
        ProcesoController.procesosPorEjecutar.clear();
        ProcesoController.procesos.clear();
        GraficoController.grafico = new String[7][99];
        Tiempo.tiempo = 0;
    }
}
