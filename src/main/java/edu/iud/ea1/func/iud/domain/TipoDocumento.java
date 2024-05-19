
package edu.iud.ea1.func.iud.domain;

public class TipoDocumento {
    private String id_tipo_doc;
    private String txt_tipo_doc;

    public String getId_tipo_doc() {
        return id_tipo_doc;
    }

    public void setId_tipo_doc(String id_tipo_doc) {
        this.id_tipo_doc = id_tipo_doc;
    }

    public String getTxt_tipo_doc() {
        return txt_tipo_doc;
    }

    public void setTxt_tipo_doc(String txt_tipo_doc) {
        this.txt_tipo_doc = txt_tipo_doc;
    }

    @Override
    public String toString() {
        return id_tipo_doc + " - " + txt_tipo_doc;
    }
    
    
}
