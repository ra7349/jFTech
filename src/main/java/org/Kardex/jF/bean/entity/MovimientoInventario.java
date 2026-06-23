package org.Kardex.jF.bean.entity;

import java.time.LocalDate;

public class MovimientoInventario {
    private String id;
    private String tipo;
    private Integer idRepuesto;
    private String codigoRepuesto;
    private String nombreRepuesto;
    private Integer cantidad;
    private String motivo;
    private LocalDate fecha;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getIdRepuesto() { return idRepuesto; }
    public void setIdRepuesto(Integer idRepuesto) { this.idRepuesto = idRepuesto; }
    public String getCodigoRepuesto() { return codigoRepuesto; }
    public void setCodigoRepuesto(String codigoRepuesto) { this.codigoRepuesto = codigoRepuesto; }
    public String getNombreRepuesto() { return nombreRepuesto; }
    public void setNombreRepuesto(String nombreRepuesto) { this.nombreRepuesto = nombreRepuesto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
