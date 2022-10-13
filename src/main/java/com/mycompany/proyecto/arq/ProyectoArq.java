/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.proyecto.arq;

import com.mycompany.proyecto.arq.Controllers.MenuController;

/**
 *
 * @author lucasbloise
 */
public class ProyectoArq {

    public static void main(String[] args) {

        do {

            MenuController.mostrarMenu();
            MenuController.seleccionarOpcion();
        } while (true);
    }
}
