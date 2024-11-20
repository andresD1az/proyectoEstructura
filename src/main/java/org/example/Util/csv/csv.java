package org.example.Util.csv;

import org.example.Dominio.Cotizante;
import org.example.Dominio.Persona;
import org.example.Dominio.Publico;
import org.example.Dominio.hijos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class csv {
    public static Persona leerUnObjeto(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            if ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                String tipo = datos[7];
                if ("false".equalsIgnoreCase(tipo)) {
                    return pasarCotizante(datos);
                } else if ("true".equalsIgnoreCase(tipo)) {
                    return pasarPublico(datos);
                } else {
                    throw new IllegalArgumentException("Tipo desconocido: " + tipo);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
        return null; // Retorna null si no hay datos o si ocurre un error
    }
    public static boolean agregarObjeto(String rutaArchivo, Persona persona) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            if (!persona.isFuncionarioPublico()) {
                Cotizante cotizante = (Cotizante) persona;
                bw.write(generarLineaCSV(cotizante));
            } else if (persona.isFuncionarioPublico()) {
                Publico publico = (Publico) persona;
                bw.write(generarLineaCSV(publico));
            }
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error escribiendo en el archivo: " + e.getMessage());
            return false;
        }
    }
    public static void eliminarPrimeraLinea(String rutaArchivo) {
        File archivoOriginal = new File(rutaArchivo);
        File archivoTemporal = new File(rutaArchivo + ".tmp");
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            // Saltar la primera línea (cabecera o línea inicial)
            br.readLine();
            // Escribir el resto del contenido en el archivo temporal
            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error procesando el archivo: " + e.getMessage());
            return; // Salir en caso de error
        }
        // Reemplazar el archivo original con el archivo temporal
        try {
            if (archivoOriginal.delete()) {
                if (!archivoTemporal.renameTo(archivoOriginal)) {
                    System.err.println("No se pudo renombrar el archivo temporal al original.");
                } else {
                    System.out.println("Primera línea eliminada correctamente.");
                }
            } else {
                System.err.println("No se pudo eliminar el archivo original.");
            }
        } catch (Exception e) {
            System.err.println("Error al reemplazar el archivo original: " + e.getMessage());
        }
    }
    public static void crearArchivoCSVVacioEnCarpeta(String rutaCarpeta, String nombreArchivo) {
        // Crear el objeto File con la ruta completa (carpeta + nombre del archivo CSV)
        File carpeta = new File(rutaCarpeta);
        File archivo = new File(carpeta, nombreArchivo);

        try {
            // Verificar si la carpeta existe, si no, crearla
            if (!carpeta.exists()) {
                if (carpeta.mkdirs()) {
                    System.out.println("Carpeta creada en: " + rutaCarpeta);
                } else {
                    System.err.println("No se pudo crear la carpeta.");
                    return;
                }
            }

            // Crear el archivo si no existe
            if (archivo.createNewFile()) {
                System.out.println("Archivo CSV vacío creado: " + archivo.getAbsolutePath());
            } else {
                System.out.println("El archivo CSV ya existe.");
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    private static String generarLineaCSV(Cotizante cotizante) {
        // Generar la línea CSV con todos los atributos de Cotizante
        return cotizante.getTipoDocumento() + ";" +
                cotizante.getCedula() + ";" +
                cotizante.getNombres() + ";" +
                cotizante.getApellidos() + ";" +
                cotizante.getUbicacionNacimiento() + ";" +
                cotizante.getUbicacionResidencia() + ";" +
                cotizante.getEdad() + ";" +
                cotizante.isFuncionarioPublico() + ";" +
                cotizante.getTrabajo() + ";" +
                cotizante.getNumeroHijos() + ";" +
                cotizante.isEmbargado() + ";" +
                cotizante.isPrepencionado() + ";" +
                cotizante.getEmpresaPensiones() + ";" +
                cotizante.getSemanasCotizadas();
    }

    private static String generarLineaCSV(Publico publico) {
        String hijosCsv = publico.getListaHijos().stream()
                .map(hijos::infocsv) // Se asume que infocsv devuelve el formato CSV para un hijo
                .reduce((a, b) -> a + "#" + b) // Concatenar hijos con separador #
                .orElse(""); // Si no hay hijos, devuelve una cadena vacía
        return generarLineaCSV((Cotizante) publico) + ";" +
                publico.isCondecoraciones() + ";" +
                publico.getInstitucionPublica() + ";" +
                hijosCsv + ";" +
                publico.isObservacionDisciplinaria();
    }
    
    private static Cotizante pasarCotizante(String[] datos) {
        Cotizante cotizante = new Cotizante();
        cotizante.setTipoDocumento(datos[0]);
        cotizante.setCedula(Integer.parseInt(datos[1]));
        cotizante.setNombres(datos[2]);
        cotizante.setApellidos(datos[3]);
        cotizante.setUbicacionNacimiento(datos[4]);
        cotizante.setUbicacionResidencia(datos[5]);
        cotizante.setEdad(Integer.parseInt(datos[6]));
        cotizante.setFuncionarioPublico(Boolean.parseBoolean(datos[7]));
        cotizante.setTrabajo(datos[8]);
        cotizante.setNumeroHijos(Integer.parseInt(datos[9]));
        cotizante.setEmbargado(Boolean.parseBoolean(datos[10]));
        cotizante.setPrepencionado(Boolean.parseBoolean(datos[11]));
        cotizante.setEmpresaPensiones(datos[12]);
        cotizante.setSemanasCotizadas(Double.parseDouble(datos[13]));
        return cotizante;
    }
    private static Publico pasarPublico(String[] datos) {
        Publico publico = new Publico();
        publico.setTipoDocumento(datos[0]);
        publico.setCedula(Integer.parseInt(datos[1]));
        publico.setNombres(datos[2]);
        publico.setApellidos(datos[3]);
        publico.setUbicacionNacimiento(datos[4]);
        publico.setUbicacionResidencia(datos[5]);
        publico.setEdad(Integer.parseInt(datos[6]));
        publico.setFuncionarioPublico(Boolean.parseBoolean(datos[7]));
        publico.setTrabajo(datos[8]);
        publico.setNumeroHijos(Integer.parseInt(datos[9]));
        publico.setEmbargado(Boolean.parseBoolean(datos[10]));
        publico.setPrepencionado(Boolean.parseBoolean(datos[11]));
        publico.setEmpresaPensiones(datos[12]);
        publico.setSemanasCotizadas(Double.parseDouble(datos[13]));
        publico.setCondecoraciones(Boolean.parseBoolean(datos[14]));
        publico.setInstitucionPublica(datos[15]);
        publico.setObservacionDisciplinaria(Boolean.parseBoolean(datos[17]));
        // Parsar lista de hijos
        if (datos.length > 16 && !datos[16].isEmpty()) {
            String[] hijosArray = datos[16].split("#");
            for (String hijoCsv : hijosArray) {
                publico.getListaHijos().add(hijos.fromCsv(hijoCsv));
            }
        }

        return publico;
    }

    public static String obtenerArchivoConDatos(String rutaCarpeta) {
        File carpeta = new File(rutaCarpeta);
        // Verificar si la ruta es una carpeta
        if (carpeta.isDirectory()) {
            // Obtener todos los archivos dentro de la carpeta que son CSV
            File[] archivos = carpeta.listFiles((dir, nombreArchivo) -> nombreArchivo.endsWith(".csv"));
            // Verificar si hay archivos CSV
            if (archivos != null && archivos.length > 0) {
                for (File archivo : archivos) {
                    if (!esArchivoVacio(archivo)) {  // Retornar si el archivo tiene datos
                        System.out.println("Archivo con datos encontrado: " + archivo.getAbsolutePath());
                        return archivo.getAbsolutePath();
                    }
                }
            }
        } else {
            System.out.println("La ruta proporcionada no es una carpeta.");
        }
        return null;  // Si no se encuentra ningún archivo con datos
    }

    public static void eliminarArchivosVacios(String rutaCarpeta) {
        File carpeta = new File(rutaCarpeta);
        // Verificar si la ruta es una carpeta
        if (carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, nombreArchivo) -> nombreArchivo.endsWith(".csv"));
            if (archivos != null && archivos.length > 0) {
                for (File archivo : archivos) {
                    if (esArchivoVacio(archivo)) {  // Verificar si el archivo está vacío o solo contiene la cabecera
                        System.out.println("El archivo está vacío o solo tiene la cabecera: " + archivo.getAbsolutePath());
                        if (archivo.delete()) {
                            System.out.println("El archivo vacío o con solo la cabecera fue eliminado: " + archivo.getAbsolutePath());
                        } else {
                            System.out.println("No se pudo eliminar el archivo: " + archivo.getAbsolutePath());
                        }
                    }
                }
            }
        } else {
            System.out.println("La ruta proporcionada no es una carpeta.");
        }
    }

    private static boolean esArchivoVacio(File archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int lineas = 0;
            // Leer el archivo línea por línea
            while ((linea = reader.readLine()) != null) {
                lineas++;
            }
            // Si el archivo tiene solo una línea (la cabecera), se considera vacío
            return lineas <= 1;
        } catch (IOException e) {
            e.printStackTrace();
            return false;  // Si ocurre un error al leer, se considera no vacío
        }
    }
}
