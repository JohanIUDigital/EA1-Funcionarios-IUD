
package edu.iud.ea1.func.iud.controller;

import edu.iud.ea1.func.iud.dao.GeneroDao;
import edu.iud.ea1.func.iud.domain.Genero;
import java.sql.SQLException;
import java.util.List;

public class GeneroController {
    private GeneroDao generoDao;
    
    public GeneroController() {
        generoDao = new GeneroDao();
    }
    
    public List<Genero> contGetGenero() throws SQLException{
        return generoDao.obtenerGenero();
    }
}
