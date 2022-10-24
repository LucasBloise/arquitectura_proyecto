package com.mycompany.proyecto.arq.Controllers;


import com.mycompany.proyecto.arq.Estado;
import com.mycompany.proyecto.arq.Proceso;

public abstract class RutinasController {

  public static void ejecutarProcesos(boolean esJSFD) {

    for(Tiempo.tiempo = 0; Tiempo.tiempo < 35; Tiempo.tiempo++){
      //Para evitar desbordamientos
      if (Tiempo.tiempo == GraficoController.grafico[0].length) break;
      if(Tiempo.tiempo == 16){
        System.out.println(getPrimerProcesoEn(Estado.BLOQUEADO).deboDesbloquear());
        System.out.println(getPrimerProcesoEn(Estado.BLOQUEADO).getTiempoBloqueado());
      }

      incrementarTiempoBloqueado();

      //Cargamos procesos a nuevo
      

      //Grabar datos previos en la tabla
      //GraficoController.grabarEnTabla(); TODO:

      String celda = "";
      for(Proceso p : ProcesoController.procesosPorEjecutar){
        if(p.getEstado() == Estado.LISTO){
            celda += p.getNombreProceso();
            GraficoController.grafico[0][Tiempo.tiempo ] = celda;
        }
      }
      celda = "";
      for(Proceso p : ProcesoController.procesosPorEjecutar){
        if(p.getEstado() == Estado.BLOQUEADO){
            celda += p.getNombreProceso();
            GraficoController.grafico[1][Tiempo.tiempo ] = celda;
        }
      }

      //Incrementar tiempo en bloqueo

    
      

      if (
        esJSFD &&
        hayProcesoEn(Estado.NUEVO) &&
        hayProcesoEnEjecucion() &&
        getPrimerProcesoEn(Estado.NUEVO).getTiempoRequerido() <
        getProcesoEnEjecucion().getTiempoRequerido()
      ) {
        GraficoController.grafico[5][Tiempo.tiempo] =
          "5P" + getProcesoEnEjecucion().getNombreProceso();
        getProcesoEnEjecucion().setEstado(Estado.LISTO);
        continue;
      }
      //Preguntamos si debemos bloquear el proceso en ejecucion

      if(hayProcesoEn(Estado.EJECUCCION) && getPrimerProcesoEn(Estado.EJECUCCION).deboTerminar()){
        GraficoController.grafico[5][Tiempo.tiempo] = "6P" + getPrimerProcesoEn(Estado.EJECUCCION).getNombreProceso();
        getPrimerProcesoEn(Estado.EJECUCCION).setEstado(Estado.TERMINADO);
        continue;
      }

      if(hayProcesoEn(Estado.EJECUCCION) && getPrimerProcesoEn(Estado.EJECUCCION).deboBloquear()){
        GraficoController.grafico[5][Tiempo.tiempo] = "3P" + getPrimerProcesoEn(Estado.EJECUCCION).getNombreProceso();
        getPrimerProcesoEn(Estado.EJECUCCION).reiniciarTiempoEjecuccion();
        getPrimerProcesoEn(Estado.EJECUCCION).setEstado(Estado.BLOQUEADO);
        continue;
      }

        
 
      //Ejecutar mi proceso si no require ser bloqueado
      if (hayProcesoEnEjecucion()) {
        GraficoController.grafico[getProcesoEnEjecucion().getNombreProceso() +
          1][Tiempo.tiempo] =
          " X ";
          getProcesoEnEjecucion().incrementarTiempoEmpleado();
        continue;
      }

      //Cargar procesos
      if (hayProcesoEn(Estado.NUEVO)) {
        GraficoController.grafico[5][Tiempo.tiempo] =
          "1P" + getPrimerProcesoEn(Estado.NUEVO).getNombreProceso();
        getPrimerProcesoEn(Estado.NUEVO).setEstado(Estado.LISTO);
        continue;
      }

      

      if (hayProcesoEn(Estado.BLOQUEADO) && getPrimerProcesoEn(Estado.BLOQUEADO).deboDesbloquear()) {
        GraficoController.grafico[5][Tiempo.tiempo] = "4P" + getPrimerProcesoEn(Estado.BLOQUEADO).getNombreProceso();
        getPrimerProcesoEn(Estado.BLOQUEADO).reiniciarTiempoBloqueado();
        getPrimerProcesoEn(Estado.BLOQUEADO).setEstado(Estado.LISTO);
        continue;
      }

      //Mandar procesos de listo a ejecucion
      if (hayProcesoEn(Estado.LISTO)) {
          GraficoController.grafico[5][Tiempo.tiempo] =
            "2P" + getPrimerProcesoEn(Estado.LISTO).getNombreProceso();
        getPrimerProcesoEn(Estado.LISTO).setEstado(Estado.EJECUCCION); 
        continue;
      }
    }
    GraficoController.imprimirTabla();
    Tiempo.tiempo = 0;
  }

  public static boolean hayProcesoEnEjecucion(){
    return getProcesoEnEjecucion() != null;
  } 

  public static Proceso getProcesoEnEjecucion(){
    Proceso proceso = null;
    for(Proceso p : ProcesoController.procesosPorEjecutar){
        if(p.getEstado() == Estado.EJECUCCION)proceso = p;
    }
    return proceso;
  }

  public static void incrementarTiempoBloqueado(){
    for(Proceso p : ProcesoController.procesosPorEjecutar){
        if(p.getEstado() == Estado.BLOQUEADO){
            p.incrementarTiempoBloqueado();
        }
    }
  }

  public static boolean hayProcesoEn(Estado setState){
    for(Proceso p : ProcesoController.procesosPorEjecutar)
        if(p.getEstado() == setState) return true; return false;
  }

  public static Proceso getPrimerProcesoEn(Estado forSetState){
    Proceso proceso = null;

    for(Proceso p : ProcesoController.procesosPorEjecutar)
        if(p.getEstado() == forSetState){
            proceso = p; 
            break;
        }
        return proceso;
  }
  

}
