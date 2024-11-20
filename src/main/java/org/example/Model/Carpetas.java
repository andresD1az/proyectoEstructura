package org.example.Model;

import org.example.Util.csv.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Carpetas {
    public static String ubicacion = "src/main/java/org/example/archivos/";
    public static String nombre="SolicitudesProcesadas_";
    public static String crearCarpeta(){
        String fechaActual = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String nombreCarpeta = nombre+ fechaActual;
        File carpeta = new File(ubicacion + nombreCarpeta);
        if (carpeta.mkdirs()) {
            System.out.println("Carpeta creada exitosamente en: " + carpeta.getAbsolutePath());
            String nombreArchivo=nombre+fechaActual;

            return ""+carpeta.getAbsolutePath();
        } else {
            System.out.println("No se pudo crear la carpeta o ya existe.");
            return "false";
        }
    }

    public static boolean comprimirCarpeta(){
        String fechaFormateada = fechaanterior();
        String nombreCarpeta = nombre + fechaFormateada;
        String carpetaARuta = ubicacion + nombreCarpeta;
        String archivoZipRuta = ubicacion + nombreCarpeta + ".zip";
        try {
            File carpetaAComprimir = new File(carpetaARuta);
            FileOutputStream fos = new FileOutputStream(archivoZipRuta);
            ZipOutputStream zos = new ZipOutputStream(fos);
            comprimirDirectorio(carpetaAComprimir, carpetaAComprimir.getName(), zos);
            zos.close();
            fos.close();
            System.out.println("Carpeta comprimida exitosamente en: " + archivoZipRuta);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void comprimirDirectorio(File archivoActual, String nombreArchivo, ZipOutputStream zos) throws IOException {
        if (archivoActual.isDirectory()) {
            // Añadir una entrada de directorio
            if (nombreArchivo.endsWith("/")) {
                zos.putNextEntry(new ZipEntry(nombreArchivo));
                zos.closeEntry();
            } else {
                zos.putNextEntry(new ZipEntry(nombreArchivo + "/"));
                zos.closeEntry();
            }

            // Listar y comprimir los archivos de la carpeta
            File[] archivos = archivoActual.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    comprimirDirectorio(archivo, nombreArchivo + "/" + archivo.getName(), zos);
                }
            }
        } else {
            // Comprimir archivo individual
            FileInputStream fis = new FileInputStream(archivoActual);
            ZipEntry zipEntry = new ZipEntry(nombreArchivo);
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            fis.close();
            }
    }
    public static boolean eliminarCarpeta() {
        String fechaFormateada = fechaanterior();
        String nombreCarpeta = nombre + fechaFormateada;
        File carpeta = new File(ubicacion + nombreCarpeta);
        return eliminarCarpetaRecursiva(carpeta);
    }
    private static boolean eliminarCarpetaRecursiva(File carpeta) {
        if (!carpeta.exists()) {
            System.out.println("La carpeta no existe.");
            return false;
        }

        if (carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    eliminarCarpetaRecursiva(archivo);
                }
            }
        }

        return carpeta.delete();
    }
    public static String fechaanterior(){
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        // Restar un día para obtener la fecha del día anterior
        LocalDate fechaDiaAnterior = fechaActual.minusDays(1);
        // Formatear la fecha (opcional)
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        String fechaFormateada = fechaDiaAnterior.format(formato);
        return fechaFormateada;
    }
}
