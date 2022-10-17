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
