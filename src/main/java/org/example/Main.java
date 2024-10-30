package org.example;

import org.example.Model.Carpetas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println("nuevo aaa  Estructura project");
        Carpetas.crearCarpeta();
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Restar un día para obtener la fecha del día anterior
        LocalDate fechaDiaAnterior = fechaActual.minusDays(1);

        // Formatear la fecha (opcional)
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaDiaAnterior.format(formato);

        System.out.println("La fecha del día anterior es: " + fechaFormateada);
    }
}