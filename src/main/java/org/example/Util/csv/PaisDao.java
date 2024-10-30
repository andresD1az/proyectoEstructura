package org.example.Util.csv;

import co.edu.uniquindio.modelo.Pais;

import java.io.IOException;

public class PaisDao extends ADao<Pais, Integer> {

    public PaisDao() throws IOException {
        super("C:\\Users\\Cristian\\Desktop\\Proyecto\\Profe\\colpensionex\\src\\main\\resources\\pais.csv");
    }
}
