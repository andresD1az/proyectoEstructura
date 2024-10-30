package org.example.Util.csv;

import co.edu.uniquindio.modelo.Departamento;

import java.io.IOException;

public class DepartamentoDao extends ADao<Departamento, String> {

    public DepartamentoDao() throws IOException {
        super("C:\\Users\\Cristian\\Desktop\\Proyecto\\Profe\\colpensionex\\src\\main\\resources\\departamento.csv");
    }
}
