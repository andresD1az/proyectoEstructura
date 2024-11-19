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
            br.readLine();
            if ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                String tipo = datos[0];
                if ("Cotizante".equalsIgnoreCase(tipo)) {
                    return parsearCotizante(datos);
                } else if ("Publico".equalsIgnoreCase(tipo)) {
                    return parsearPublico(datos);
                } else {
                    throw new IllegalArgumentException("Tipo desconocido: " + tipo);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
        return null; // Retorna null si no hay datos o si ocurre un error
    }
    public static void agregarObjeto(String rutaArchivo, Persona persona) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            if (persona instanceof Cotizante) {
                Cotizante cotizante = (Cotizante) persona;
                bw.write("Cotizante," + generarLineaCSV(cotizante));
            } else if (persona instanceof Publico) {
                Publico publico = (Publico) persona;
                bw.write("Publico," + generarLineaCSV(publico));
            }
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error escribiendo en el archivo: " + e.getMessage());
        }
    }
    
    public static void eliminarPrimeraLinea(String rutaArchivo) {
        File archivoOriginal = new File(rutaArchivo);
        File archivoTemporal = new File(rutaArchivo + ".tmp");
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            String cabecera = br.readLine();
            if (cabecera != null) {
                bw.write(cabecera);
                bw.newLine();
            }
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error eliminando la primera línea: " + e.getMessage());
        }
        if (!archivoOriginal.delete() || !archivoTemporal.renameTo(archivoOriginal)) {
            System.err.println("Error al reemplazar el archivo original.");
        }
    }
    
    private static String generarLineaCSV(Cotizante cotizante) {
        return cotizante.getCedula() + ";" +
                cotizante.getTipoDocumento() + ";" +
                cotizante.getNombres() + ";" +
                cotizante.getApellidos() + ";" +
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
                .map(hijos::infocsv)
                .reduce((a, b) -> a + "#" + b)
                .orElse("");
        return generarLineaCSV((Cotizante) publico) + ";" +
                publico.isCondecoraciones() + ";" +
                publico.getInstitucionPublica() + ";" +
                publico.isObservacionDisciplinaria() + ";" +
                hijosCsv;
    }
    
    private static Cotizante parsearCotizante(String[] datos) {
        Cotizante cotizante = new Cotizante();
        cotizante.setCedula(Integer.parseInt(datos[1]));
        cotizante.setTipoDocumento(datos[2]);
        cotizante.setNombres(datos[3]);
        cotizante.setApellidos(datos[4]);
        cotizante.setEdad(Integer.parseInt(datos[5]));
        cotizante.setFuncionarioPublico(Boolean.parseBoolean(datos[6]));
        cotizante.setTrabajo(datos[7]);
        cotizante.setNumeroHijos(Integer.parseInt(datos[8]));
        cotizante.setEmbargado(Boolean.parseBoolean(datos[9]));
        cotizante.setPrepencionado(Boolean.parseBoolean(datos[10]));
        cotizante.setEmpresaPensiones(datos[11]);
        cotizante.setSemanasCotizadas(Double.parseDouble(datos[12]));
        return cotizante;
    }
    private static Publico parsearPublico(String[] datos) {
        Publico publico = new Publico();
        publico.setCedula(Integer.parseInt(datos[1]));
        publico.setTipoDocumento(datos[2]);
        publico.setNombres(datos[3]);
        publico.setApellidos(datos[4]);
        publico.setEdad(Integer.parseInt(datos[5]));
        publico.setFuncionarioPublico(Boolean.parseBoolean(datos[6]));
        publico.setTrabajo(datos[7]);
        publico.setNumeroHijos(Integer.parseInt(datos[8]));
        publico.setEmbargado(Boolean.parseBoolean(datos[9]));
        publico.setPrepencionado(Boolean.parseBoolean(datos[10]));
        publico.setEmpresaPensiones(datos[11]);
        publico.setSemanasCotizadas(Double.parseDouble(datos[12]));
        publico.setCondecoraciones(Boolean.parseBoolean(datos[13]));
        publico.setInstitucionPublica(datos[14]);
        publico.setObservacionDisciplinaria(Boolean.parseBoolean(datos[15]));

        // Parsear lista de hijos
        String hijosCsv = datos[16];
        if (!hijosCsv.isEmpty()) {
            String[] hijosArray = hijosCsv.split("\\|");
            for (String hijoCsv : hijosArray) {
                publico.getListaHijos().add(hijos.fromCsv(hijoCsv));
            }
        }

        return publico;
    }

    public static String obtenerYEliminarArchivoVacio(String rutaCarpeta) {
        File carpeta = new File(rutaCarpeta);

        // Verificar si la ruta es una carpeta
        if (carpeta.isDirectory()) {
            // Obtener todos los archivos dentro de la carpeta que son CSV
            File[] archivos = carpeta.listFiles((dir, nombreArchivo) -> nombreArchivo.endsWith(".csv"));

            // Verificar si hay archivos CSV
            if (archivos != null && archivos.length > 0) {
                for (File archivo : archivos) {
                    if (esArchivoVacio(archivo)) {  // Verificar si el archivo está vacío o solo contiene la cabecera
                        System.out.println("El archivo está vacío o solo tiene la cabecera: " + archivo.getAbsolutePath());
                        if (archivo.delete()) {
                            System.out.println("El archivo vacío o con solo la cabecera fue eliminado: " + archivo.getAbsolutePath());
                        } else {
                            System.out.println("No se pudo eliminar el archivo: " + archivo.getAbsolutePath());
                        }
                    } else {
                        // Si el archivo no está vacío y tiene datos, retornar su ubicación
                        return archivo.getAbsolutePath();
                    }
                }
            }
        } else {
            System.out.println("La ruta proporcionada no es una carpeta.");
        }

        return null;  // Si no se encuentra ningún archivo válido
    }

    // Método auxiliar para verificar si el archivo está vacío o solo tiene la cabecera
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
