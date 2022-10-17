/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto.arq.Controllers;

import java.util.Scanner;

/**
 *
 * @author lucasbloise
 */
public class MenuController {
    private static Scanner sc = new Scanner(System.in);
    private static String opcionSeleccionada;

    public static void mostrarMenu() {
        System.out.println("-----------------------------------");
        System.out.println("1 - Establecer tiempo entrada salida");
        System.out.println("2 - Cargar Procesos");
        System.out.println("3 - Mostrar grafica de JSF");
        System.out.println("4 - Mostrar grafica de JSF/D");
        System.out.println("Pulse otra tecla para salir");
        System.out.println("-----------------------------------");
    }

    public static void seleccionarOpcion() {
        opcionSeleccionada = sc.nextLine();
        switch (opcionSeleccionada) {
            case "1":
                System.out.print("\033[H\033[2J");
                System.out.println("Ingrese el tiempo de entrada-salida");
                InfoGlobal.setTiempoEntradaSalida(sc.nextInt());
                sc.nextLine();
                break;
            case "2":
                System.out.print("\033[H\033[2J");
                System.out.println("Va a cargar procesos.");
                ProcesoController.cargarProcesos();
                break;
            case "3":
                // ejecutar jsf
                System.out.print("\033[H\033[2J");
                RutinasController.ejecutarProcesos();
                System.out.print("Presiones cualquier tecla para continuar");
                sc.nextLine();
                break;
            case "4":
                // jejecutar jsf d
                break;
            default:
                System.exit(0);

        }
    }

}
