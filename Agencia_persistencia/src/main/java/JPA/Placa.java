/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author SDavidLedesma
 */
@Entity
@Table(name = "placas")
public class Placa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "fecha_emision", nullable = false)
    //private Date fechaEmision;
    // @Column(name = "fecha_recepcion", nullable = false)
    // private Date fechaRecepcion;
    @Column(name = "numero_serie_automovil", nullable = false)
    private String numeroSerieAutomovil;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "linea", nullable = false)
    private String linea;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "modelo", nullable = false)
    private int modelo;

    @Column(name = "placa")
    private String placa;

    @Column(name = "licencia")
    private String licencia;

    public Placa() {
    }

    public Placa(String numeroSerieAutomovil, String marca, String linea, String color, int modelo, String placa, String licencia) {
        this.numeroSerieAutomovil = numeroSerieAutomovil;
        this.marca = marca;
        this.linea = linea;
        this.color = color;
        this.modelo = modelo;
        this.placa = placa;
        this.licencia = licencia;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNumeroSerieAutomovil() {
        return numeroSerieAutomovil;
    }

    public void setNumeroSerieAutomovil(String numeroSerieAutomovil) {
        this.numeroSerieAutomovil = numeroSerieAutomovil;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Placa{" + "id=" + id + ", numeroSerieAutomovil=" + numeroSerieAutomovil + ", marca=" + marca + ", linea=" + linea + ", color=" + color + ", modelo=" + modelo + ", placa=" + placa + ", licencia=" + licencia + '}';
    }

}
