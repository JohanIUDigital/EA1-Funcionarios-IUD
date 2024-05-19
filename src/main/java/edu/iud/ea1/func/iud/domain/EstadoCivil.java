
package edu.iud.ea1.func.iud.domain;

public class EstadoCivil {
        private String id_est_civil;
        private String txt_est_civil;

    public String getId_est_civil() {
        return id_est_civil;
    }

    public void setId_est_civil(String id_est_civil) {
        this.id_est_civil = id_est_civil;
    }

    public String getTxt_est_civil() {
        return txt_est_civil;
    }

    public void setTxt_est_civil(String txt_est_civil) {
        this.txt_est_civil = txt_est_civil;
    }

    @Override
    public String toString() {
        return id_est_civil + " - " + txt_est_civil;
    }
        
        
}
