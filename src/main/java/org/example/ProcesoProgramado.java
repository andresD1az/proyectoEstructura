package org.example;
import org.example.Model.Carpetas;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProcesoProgramado {

    public static void iniciarProcesoDiario() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // Calcula el tiempo en milisegundos hasta la próxima 1:00 AM
        long delayHastaLaUna = calcularDelayHastaLaUnaAM();
        // Programa la tarea para ejecutarse en la próxima 1:00 AM y luego cada 24 horas
        scheduler.scheduleAtFixedRate(() -> {
            realizarProceso();
        }, delayHastaLaUna, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
    }

    private static long calcularDelayHastaLaUnaAM() {
        Calendar ahora = Calendar.getInstance();
        Calendar unaAM = (Calendar) ahora.clone();
        // Configura la hora de unaAM a 1:00 AM del día siguiente
        unaAM.set(Calendar.HOUR_OF_DAY, 1);
        unaAM.set(Calendar.MINUTE, 0);
        unaAM.set(Calendar.SECOND, 0);
        unaAM.set(Calendar.MILLISECOND, 0);
        // Si ya pasó la 1:00 AM hoy, programa para mañana
        if (ahora.after(unaAM)) {
            unaAM.add(Calendar.DAY_OF_YEAR, 1);
        }
        return unaAM.getTimeInMillis() - ahora.getTimeInMillis();
    }

    private static void realizarProceso() {
        System.out.println("Ejecutando proceso a la 1:00 AM: " + new Date());
        Carpetas carpetas  = new Carpetas();
        if (carpetas.comprimirCarpeta()){
            if (carpetas.eliminarCarpeta()){
                carpetas.crearCarpeta();
            }
        }
    }
    public static void main(String[] args) {
        iniciarProcesoDiario();
    }
}
