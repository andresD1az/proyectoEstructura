package org.example.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Carpetas {
    public static String ubicacion = "src/main/java/org/example/archivos/";
    public static String nombre="SolicitudesProcesadas_";
    public static boolean crearCarpeta(){
        String fechaActual = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String nombreCarpeta = nombre+ fechaActual;
        File carpeta = new File(ubicacion + nombreCarpeta);
        if (carpeta.mkdirs()) {
            System.out.println("Carpeta creada exitosamente en: " + carpeta.getAbsolutePath());
            return true;
        } else {
            System.out.println("No se pudo crear la carpeta o ya existe.");
            return false;
        }
    }
    public static boolean comprimirCarpeta(String fecha){
        String nombreCarpeta= nombre+ fecha;
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
}
