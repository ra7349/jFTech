package org.Kardex.jF.bean.entity;

public class Tecnico extends Persona {
    private String codigo;
    private String especialidad;

    public String getCodigo()                  { return codigo; }
    public void   setCodigo(String c)          { this.codigo = c; }
    public String getEspecialidad()            { return especialidad; }
    public void   setEspecialidad(String e)    { this.especialidad = e; }
}
