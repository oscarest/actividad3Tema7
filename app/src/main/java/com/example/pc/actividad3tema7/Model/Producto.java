package com.example.pc.actividad3tema7.Model;

public class Producto {
    private Long id;
    private String nombre;
    private Integer cantidad;
    private Long idSeccion;

    public Producto() {
        this.id = null;
        this.nombre = "";
        this.cantidad = 0;
        this.idSeccion = null;
    }

    public Producto(String nombre, Integer cantidad, Long idSeccion) {
        this.id = null;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.idSeccion = idSeccion;
    }

    public Producto(Long id, String nombre, Integer cantidad, Long idSeccion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.idSeccion = idSeccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    @Override
    public String toString() {
        return "Producto {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", idseccion='" + idSeccion + '\'' +
                '}';
    }
}
