package org.example;

import org.example.Dominio.Cotizante;
import org.example.Dominio.CotizanteNegro;
import org.example.Dominio.Publico;
import org.example.Util.csv.CsvCotizantesNegros;
import org.example.Util.csv.csv;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PruebaNegra {
    public static void main(String[] args) {
        //Pruebas de los embargados
        String rutanegra = csv.obtenerArchivoConDatos("src/main/java/org/example/archivos/ListaNegra");
        String rutaProceso = csv.obtenerArchivoConDatos("src/main/java/org/example/archivos/SolicitudesEnProceso");
        Cotizante cotizante = (Cotizante) csv.leerUnObjeto(rutaProceso);
        if (cotizante.isEmbargado()){
            String fechaActual = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
            CotizanteNegro cotizanteNegro =new CotizanteNegro(cotizante,fechaActual);
            CsvCotizantesNegros.agregarObjeto(rutanegra,cotizanteNegro);
        }
    }
}
