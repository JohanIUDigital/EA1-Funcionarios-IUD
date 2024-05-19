
package edu.iud.ea1.func.iud.domain;

public class Genero {
    private String id_genero;
    private String txt_genero;

    public String getId_genero() {
        return id_genero;
    }

    public void setId_genero(String id_genero) {
        this.id_genero = id_genero;
    }

    public String getTxt_genero() {
        return txt_genero;
    }

    public void setTxt_genero(String txt_genero) {
        this.txt_genero = txt_genero;
    }

    @Override
    public String toString() {
        return id_genero + " - " + txt_genero;
    }
    
    
}
