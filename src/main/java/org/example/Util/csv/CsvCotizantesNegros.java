package org.example.Util.csv;

import org.example.Dominio.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvCotizantesNegros {

    // Método para leer una lista de objetos CotizanteNegro desde el archivo CSV
    public static List<CotizanteNegro> leerObjetos(String rutaArchivo) {
        List<CotizanteNegro> cotizantesNegros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                CotizanteNegro cotizanteNegro = datos[7].equalsIgnoreCase("true") ? pasarPublico(datos) : pasarCotizante(datos);
                cotizantesNegros.add(cotizanteNegro);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
        return cotizantesNegros;
    }

    // Método para agregar un solo objeto CotizanteNegro al archivo
    public static boolean agregarObjeto(String rutaArchivo, CotizanteNegro cotizanteNegro) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            Cotizante cotizante = cotizanteNegro.getCotizante();
            if (cotizante instanceof Publico) {
                bw.write(generarLineaCSV((Publico) cotizante, cotizanteNegro.getFecha()));
            } else {
                bw.write(generarLineaCSV(cotizante, cotizanteNegro.getFecha()));
            }
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error escribiendo en el archivo: " + e.getMessage());
            return false;
        }
    }

    // Método auxiliar para generar la línea CSV de un objeto Cotizante
    private static String generarLineaCSV(Cotizante cotizante, String fecha) {
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
                cotizante.getSemanasCotizadas() + ";" +
                fecha;
    }

    // Método auxiliar para generar la línea CSV de un objeto Publico
    private static String generarLineaCSV(Publico publico, String fecha) {
        String hijosCsv = publico.getListaHijos().stream()
                .map(hijos::infocsv) // Método para serializar hijos en CSV
                .reduce((a, b) -> a + "#" + b)
                .orElse("");
        return generarLineaCSV((Cotizante) publico, fecha) + ";" +
                publico.isCondecoraciones() + ";" +
                publico.getInstitucionPublica() + ";" +
                hijosCsv + ";" +
                publico.isObservacionDisciplinaria();
    }

    // Método para procesar un Cotizante desde datos CSV
    private static CotizanteNegro pasarCotizante(String[] datos) {
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
        CotizanteNegro cotizanteNegro = new CotizanteNegro(cotizante, datos[14]);
        return cotizanteNegro;
    }

    // Método para procesar un Publico desde datos CSV
    private static CotizanteNegro pasarPublico(String[] datos) {
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
        if (datos.length > 16 && !datos[16].isEmpty()) {
            String[] hijosArray = datos[16].split("#");
            for (String hijoCsv : hijosArray) {
                publico.getListaHijos().add(hijos.fromCsv(hijoCsv));
            }
        }
        CotizanteNegro cotizanteNegro = new CotizanteNegro(publico, datos[18]);
        return cotizanteNegro;
    }
}