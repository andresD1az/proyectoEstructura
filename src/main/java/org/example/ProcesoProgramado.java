package org.example;
import org.example.Model.Carpetas;
import org.example.Util.csv.csv;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProcesoProgramado {
    public static void iniciarProcesos() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Programa el proceso diario para ejecutarse a la 1:00 AM
        long delayHastaLaUna = calcularDelayHastaLaUnaAM();
        scheduler.scheduleAtFixedRate(() -> {
            realizarProcesoDiario();
        }, delayHastaLaUna, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);

        // Programa el proceso horario para ejecutarse cada hora
        long delayHastaLaProximaHora = calcularDelayHastaLaProximaHora();
        scheduler.scheduleAtFixedRate(() -> {
            realizarProcesoHorario();
        }, delayHastaLaProximaHora, TimeUnit.HOURS.toMillis(1), TimeUnit.MILLISECONDS);
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

    private static long calcularDelayHastaLaProximaHora() {
        Calendar ahora = Calendar.getInstance();
        Calendar proximaHora = (Calendar) ahora.clone();

        // Configura el minuto y segundo a 0 para la próxima hora exacta
        proximaHora.set(Calendar.MINUTE, 0);
        proximaHora.set(Calendar.SECOND, 0);
        proximaHora.set(Calendar.MILLISECOND, 0);
        proximaHora.add(Calendar.HOUR_OF_DAY, 1);

        return proximaHora.getTimeInMillis() - ahora.getTimeInMillis();
    }

    private static void realizarProcesoDiario() {
        System.out.println("Ejecutando proceso diario a la 1:00 AM: " + new Date());
        System.out.println("Ejecutando proceso a la 1:00 AM: " + new Date());
        Carpetas carpetas  = new Carpetas();
        String nombre="SolicitudesProcesadas_";
        String fechaActual = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String nombreArchivo = nombre+ fechaActual;
        if (carpetas.comprimirCarpeta()){
            if (carpetas.eliminarCarpeta()){
                String carpeta = carpetas.crearCarpeta();
                csv.crearArchivoCSVVacioEnCarpeta(carpeta,nombreArchivo);
            }
        }
    }

    private static void realizarProcesoHorario() {
        System.out.println("Ejecutando proceso cada hora: " + new Date());
        for (int i=1; i<=5; i++){

        }
    }

    public static void main(String[] args) {
        iniciarProcesos();
    }
}
