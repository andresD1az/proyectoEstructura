package org.example.Model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Carpetas {
    public boolean crearCarpeta(){
        String ubicacion = "src/main/java/org/example/archivos/";
        String fechaActual = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String nombreCarpeta = "SolicitudesProcesadas_" + fechaActual;
        File carpeta = new File(ubicacion + nombreCarpeta);
        if (carpeta.mkdirs()) {
            System.out.println("Carpeta creada exitosamente en: " + carpeta.getAbsolutePath());
            return true;
        } else {
            System.out.println("No se pudo crear la carpeta o ya existe.");
            return false;
        }
    }
}
