/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto.arq.Controllers;

import java.util.ArrayList;
import java.util.Scanner;

import com.mycompany.proyecto.arq.Proceso;

/**
 *
 * @author lucasbloise
 */
public class ProcesoController {
  static ArrayList<Proceso> procesos = new ArrayList();
  private static Scanner sc = new Scanner(System.in);

  public static void cargarProcesos() {

    for (int i = 0; i < 3; i++) {
      Proceso p = new Proceso();

      System.out.println("Proceso: " + (i + 1));
      p.setNombreProceso(i + 1);
      System.out.println("Cantidad de Rafagas de procesamiento");
      p.setCantidadCiclos(sc.nextInt());
      System.out.println("Tiempo de llegada del proceso");
      p.setTiempoDeLlegada(sc.nextInt());

      for (int j = 0; j < p.getCantidadCiclos(); j++) {
        System.out.println("Valor de Rafaga N" + (j + 1));
        int tiempoRafaga = sc.nextInt();
        p.incrementarTiempo(tiempoRafaga);
        p.agregarTiempoProcesamiento(tiempoRafaga);
      }
      procesos.add(p);
    }
  }

}
