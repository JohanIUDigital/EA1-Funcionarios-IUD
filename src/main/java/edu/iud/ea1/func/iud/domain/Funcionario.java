
package edu.iud.ea1.func.iud.domain;

import java.util.Date;

public class Funcionario {
    private int id;
    private String numDocumento;
    private String tipoDocumento;
    private String nombre;
    private String apellido;
    private String idEstadoCivil;
    private String estadoCivil;
    private String idGenero;
    private String genero;
    private String direccion;
    private String telefono;
    private Date fechaNacimiento;
    private int idGrupoFamiliar;
    private int idInfoAcademica;

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(String idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdGrupoFamiliar() {
        return idGrupoFamiliar;
    }

    public void setIdGrupoFamiliar(int idGrupoFamiliar) {
        this.idGrupoFamiliar = idGrupoFamiliar;
    }

    public int getIdInfoAcademica() {
        return idInfoAcademica;
    }

    public void setIdInfoAcademica(int idInfoAcademica) {
        this.idInfoAcademica = idInfoAcademica;
    }

    @Override
    public String toString() {
        return tipoDocumento + " " + numDocumento + " " + nombre + " " + apellido;
    }

    
    
}
