
package edu.iud.ea1.func.iud.domain;

public class Familiar {
    private String nombreCompleto;
    private String parentesco;
    private String idGenero;
    private String genero;
    private String telefono;
    private String direccion;

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Familiar{" + "nombreCompleto=" + nombreCompleto + ", parentesco=" + parentesco + ", idGenero=" + idGenero + ", genero=" + genero + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }
    
    
    
}
