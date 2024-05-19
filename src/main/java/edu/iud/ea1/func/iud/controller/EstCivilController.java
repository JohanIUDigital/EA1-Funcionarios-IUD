
package edu.iud.ea1.func.iud.controller;

import edu.iud.ea1.func.iud.dao.EstadoCivilDao;
import edu.iud.ea1.func.iud.domain.EstadoCivil;
import java.sql.SQLException;
import java.util.List;
public class EstCivilController {
        
    //lógica pesada/de negocio de la aplicación
    private final EstadoCivilDao estadoCivilDao;
    
    public EstCivilController() {
        estadoCivilDao = new EstadoCivilDao();
    }
    
    public List<EstadoCivil> contGetEstCiviles() throws SQLException{
        return estadoCivilDao.obtenerEstCivil();
    }
}
