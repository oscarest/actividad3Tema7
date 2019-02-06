package com.example.pc.actividad3tema7.Model;

public class Seccion {
    private Long id;
    private String descripcion;
    private Integer minStock;
    private Integer maxStock;

    public Seccion() {
        this.id = null;
        this.descripcion = "";
        this.minStock = 0;
        this.maxStock = 0;
    }

    public Seccion(String descripcion, Integer minStock, Integer maxStock) {
        this.id = null;
        this.descripcion = descripcion;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }

    public Seccion(Long id, String descripcion, Integer minStock, Integer maxStock) {
        this.id = id;
        this.descripcion = descripcion;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String nombre) {
        this.descripcion = descripcion;
    }

    public Integer getMinstock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Integer getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }

    @Override
    public String toString() {
        return "Seccion {" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", minStock=" + minStock +
                ", maxStock='" + maxStock + '\'' +
                '}';
    }
}
