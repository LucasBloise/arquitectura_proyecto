package com.mycompany.proyecto.arq.Controllers;

import javax.swing.*;

public class GraficoController {
    public static String[][] grafico = new String[7][99];

    public static void imprimirTabla() {
        
        String posicion;
        System.out.println(
            GraficoController.grafico
            );
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 35; j++) {
                posicion = grafico[i][j];
                if (i == 0 && j == 0)
                    System.out.print("| Listo     |");
                if (i == 1 && j == 0)
                    System.out.print("| Bloqueado |");
                if (i == 2 && j == 0)
                    System.out.print("| P1        |");
                if (i == 3 && j == 0)
                    System.out.print("| P2        |");
                if (i == 4 && j == 0)
                    System.out.print("| P3        |");
                if (i == 5 && j == 0)
                    System.out.print("| SO        |");
                if (i == 6 && j == 0)
                    System.out.print("| Tiempo    |");

                if (posicion == null) {
                    posicion = "   ";
                }
                if (i == 6) {
                    posicion = "" + j;
                    System.out.print("");
                }
                while (posicion.length() < 3) {
                    posicion += " ";
                }
                System.out.print(posicion + " |");

            }
            System.out.println();

            System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        }
    }

    public static void imprimirTabla2() {
        JFrame f;
        f = new JFrame();
        String data[][] = grafico;
        String column[] = new String[grafico[0].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = i + "";
        }
        JTable jt = new JTable(data, column);
        jt.setBounds(50, 50, 400, 400);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(300, 400);
        f.setVisible(true);
    }

}
