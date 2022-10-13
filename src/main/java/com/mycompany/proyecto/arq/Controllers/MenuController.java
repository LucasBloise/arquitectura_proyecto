/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto.arq.Controllers;

import java.util.Scanner;

import com.mycompany.proyecto.arq.Proceso;

/**
 *
 * @author lucasbloise
 */
public class MenuController {
    private static Scanner sc = new Scanner(System.in);

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
        String opcionSeleccionada = sc.nextLine();

        switch (opcionSeleccionada) {
            case "1":
                System.out.print("\033[H\033[2J");
                System.out.println("Ingrese el tiempo de entrada-salida");
                cargarTiempoES();
                break;
            case "2":
                System.out.println("Va a cargar procesos.");
                ProcesoController.cargarProcesos();
                for (Proceso proceso : ProcesoController.procesos) {
                    proceso.imprimir();
                }
                break;
            case "3":
                RutinasController.ejecutarProcesos();
                // ejecutar jsf
                break;
            case "4":
                // jejecutar jsf d
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void cargarTiempoES() {
        int tiempEntradaSalida = sc.nextInt();
        InfoGlobal.setTiempoEntradaSalida(tiempEntradaSalida);
    }
}
