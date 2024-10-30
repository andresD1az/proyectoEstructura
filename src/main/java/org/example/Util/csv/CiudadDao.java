package org.example.Util.csv;

import co.edu.uniquindio.modelo.Ciudad;

import java.io.IOException;

public class CiudadDao extends ADao<Ciudad,String>{
    public CiudadDao() throws IOException {
        super("C:\\Users\\Cristian\\Desktop\\Proyecto\\Profe\\colpensionex\\src\\main\\resources\\ciudades.csv");
    }
}
