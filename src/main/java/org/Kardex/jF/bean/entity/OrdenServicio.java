package org.Kardex.jF.bean.entity;

import java.time.LocalDate;

public class OrdenServicio {
    private String    id;
    private String    codigo;
    private String    descripcionFalla;
    private String    diagnostico;
    private String    solucion;
    private String    estado;           // valores del ENUM
    private double    costoEstimado;
    private Double    costoFinal;
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private Integer   idEquipo;
    private Integer   idTecnico;
    private Integer   idCliente;
    private Integer   idServicio;
    // join helpers
    private String    codigoEquipo;
    private String    nombreTecnico;
    private String    nombreCliente;
    private String    nombreServicio;

    public String    getId()                          { return id; }
    public void      setId(String id)                 { this.id = id; }
    public String    getCodigo()                      { return codigo; }
    public void      setCodigo(String c)              { this.codigo = c; }
    public String    getDescripcionFalla()            { return descripcionFalla; }
    public void      setDescripcionFalla(String d)    { this.descripcionFalla = d; }
    public String    getDiagnostico()                 { return diagnostico; }
    public void      setDiagnostico(String d)         { this.diagnostico = d; }
    public String    getSolucion()                    { return solucion; }
    public void      setSolucion(String s)            { this.solucion = s; }
    public String    getEstado()                      { return estado; }
    public void      setEstado(String e)              { this.estado = e; }
    public double    getCostoEstimado()               { return costoEstimado; }
    public void      setCostoEstimado(double c)       { this.costoEstimado = c; }
    public Double    getCostoFinal()                  { return costoFinal; }
    public void      setCostoFinal(Double c)          { this.costoFinal = c; }
    public LocalDate getFechaApertura()               { return fechaApertura; }
    public void      setFechaApertura(LocalDate f)    { this.fechaApertura = f; }
    public LocalDate getFechaCierre()                 { return fechaCierre; }
    public void      setFechaCierre(LocalDate f)      { this.fechaCierre = f; }
    public Integer   getIdEquipo()                    { return idEquipo; }
    public void      setIdEquipo(Integer i)           { this.idEquipo = i; }
    public Integer   getIdTecnico()                   { return idTecnico; }
    public void      setIdTecnico(Integer i)          { this.idTecnico = i; }
    public Integer   getIdCliente()                   { return idCliente; }
    public void      setIdCliente(Integer i)          { this.idCliente = i; }
    public Integer   getIdServicio()                  { return idServicio; }
    public void      setIdServicio(Integer i)         { this.idServicio = i; }
    public String    getCodigoEquipo()                { return codigoEquipo; }
    public void      setCodigoEquipo(String c)        { this.codigoEquipo = c; }
    public String    getNombreTecnico()               { return nombreTecnico; }
    public void      setNombreTecnico(String n)       { this.nombreTecnico = n; }
    public String    getNombreCliente()               { return nombreCliente; }
    public void      setNombreCliente(String n)       { this.nombreCliente = n; }
    public String    getNombreServicio()              { return nombreServicio; }
    public void      setNombreServicio(String n)      { this.nombreServicio = n; }
}
