package org.example.Util.csv;

import org.example.Dominio.Cotizante;
import org.example.Dominio.Publico;

import java.io.*;
import java.util.ArrayList;

public class csv {
    private static final String CABECERA = "tipo,embargado,prepencionado,empresaPensiones,semanasCotizadas,condecoraciones,institucionPublica,observDisciplinaria,listahijos";

    // Método para leer el archivo CSV
    public static ArrayList<Cotizante> leerArchivo(String archivo) {
        ArrayList<Cotizante> objetos = new ArrayList<>();
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            // Leer encabezados
            String encabezado = br.readLine();

            // Leer cada línea del archivo
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(","); // Separar por comas

                // Identificar el tipo de objeto
                String tipo = datos[0]; // Primera columna es el tipo
                if (tipo.equalsIgnoreCase("Cotizante")) {
                    Cotizante cotizante = new Cotizante();
                    cotizante.setEmbargado(Boolean.parseBoolean(datos[1]));
                    cotizante.setPrepencionado(Boolean.parseBoolean(datos[2]));
                    cotizante.setEmpresaPenciones(datos[3]);
                    cotizante.setSemanasCotizadas(Double.parseDouble(datos[4]));
                    objetos.add(cotizante);
                } else if (tipo.equalsIgnoreCase("Publico")) {
                    Publico publico = new Publico();
                    publico.setEmbargado(Boolean.parseBoolean(datos[1]));
                    publico.setPrepencionado(Boolean.parseBoolean(datos[2]));
                    publico.setEmpresaPenciones(datos[3]);
                    publico.setSemanasCotizadas(Double.parseDouble(datos[4]));
                    publico.setCondecoraciones(Boolean.parseBoolean(datos[5]));
                    publico.setIntitucionPublica(datos[6]);
                    publico.setObserDisiplinaria(Boolean.parseBoolean(datos[7]));

                    // Procesar lista de hijos
                    ArrayList<String> listaHijos = new ArrayList<>();
                    if (!datos[8].isEmpty()) {
                        for (String hijo : datos[8].split(";")) {
                            listaHijos.add(hijo.trim());
                        }
                    }
                    publico.setListahijos(listaHijos);
                    objetos.add(publico);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return objetos;
    }

    // Método para agregar un objeto al archivo CSV
    public static void agregarObjeto(String archivo, Cotizante objeto) {
        boolean archivoExistente = new File(archivo).exists();

        try (FileWriter fw = new FileWriter(archivo, true); BufferedWriter bw = new BufferedWriter(fw)) {
            // Si el archivo no existe, escribir la cabecera
            if (!archivoExistente) {
                bw.write(CABECERA);
                bw.newLine();
            }

            // Escribir el objeto en formato CSV
            if (objeto instanceof Publico) {
                Publico publico = (Publico) objeto;
                //String hijos = String.join(";", publico.getListahijos());
                bw.write(String.format(
                        "Publico,%b,%b,%s,%.2f,%b,%s,%b,%s",
                        publico.isEmbargado(),
                        publico.isPrepencionado(),
                        publico.getEmpresaPenciones(),
                        publico.getSemanasCotizadas(),
                        publico.isCondecoraciones(),
                        publico.getIntitucionPublica(),
                        publico.isObserDisiplinaria(),
                        hijos
                ));
            } else {
                bw.write(String.format(
                        "Cotizante,%b,%b,%s,%.2f,,,,",
                        objeto.isEmbargado(),
                        objeto.isPrepencionado(),
                        objeto.getEmpresaPenciones(),
                        objeto.getSemanasCotizadas()
                ));
            }
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
