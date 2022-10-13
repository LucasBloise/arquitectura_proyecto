package com.mycompany.proyecto.arq.Controllers;

public class GraficoController {
    public static String[][] grafico = new String[7][1000];

    public static void imprimirTabla() {
        String posicion;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 1000; j++) {
                posicion = grafico[i][j];
                if (posicion == null) {
                    posicion = "   ";
                }
                System.out.print(posicion);
            }
            System.out.println();
        }
    }
}
