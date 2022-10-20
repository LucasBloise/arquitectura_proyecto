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
        System.out.print("\033[H\033[2J");
        System.out.println("-----------------------------------");
        System.out.println("1 - Establecer tiempo entrada salida");
        System.out.println("2 - Cargar Procesos");
        System.out.println("3 - Mostrar grafica de JSF");
        System.out.println("4 - Mostrar grafica de JSF/D");
        System.out.println("5 - Limpiar cola de Procesos");
        System.out.println("6 - Carga automatica de Procesos");
        System.out.println("Pulse enter para salir");
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
                ProcesoController.procesos.clear();
                ProcesoController.procesosPorEjecutar.clear();
                System.out.print("\033[H\033[2J");
                System.out.println("Va a cargar procesos.");
                ProcesoController.cargarProcesos();
                break;
            case "3":
                // ejecutar jsf
                System.out.print("\033[H\033[2J");
                if (!ProcesoController.procesos.isEmpty()) {
                    RutinasController.ejecutarProcesos();
                    System.out.print("Presiones cualquier tecla para continuar");
                    sc.nextLine();

                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.println("No tienes procesos cargados");
                    System.out.println("Presiona enter para volver al menu");
                    sc.nextLine();
                }
                break;

            case "4":
                // jejecutar jsf d
                if (!ProcesoController.procesos.isEmpty()) {
                    RutinasController.ejecutarProcesos();
                    System.out.print("Presiones cualquier tecla para continuar");
                    sc.nextLine();

                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.println("No tienes procesos cargados");
                    System.out.println("Presiona enter para volver al menu");
                    sc.nextLine();
                }
                break;
            case "5":
                ProcesoController.procesos.clear();
                ProcesoController.procesosPorEjecutar.clear();
                System.out.println("Procesos eliminados");
                System.out.println("Presiona enter para volver al menu");
                sc.nextLine();
                break;
            case "6":
                ProcesoController.procesos.clear();
                ProcesoController.procesosPorEjecutar.clear();
                System.out.print("\033[H\033[2J");
                System.out.println("Va a cargar procesos automaticamente presione enter.");
                sc.nextLine();
                ProcesoController.cargaAutomatica();
                break;
            default:
                System.exit(0);

        }
    }

}
