package com.javaapp.veterinaria.Model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcita")
    private Integer idCita;

    @Column(name = "nombrec", nullable = false, length = 50)
    private String nombreC;

    @Column(name = "apellidoc", nullable = false, length = 50)
    private String apellidoC;

    @Column(name = "telefonoc", length = 15)
    private String telefonoC;

    @Column(name = "correoc", length = 50)
    private String correoC;

    @Column(name = "fechac", nullable = false)
    private LocalDate fechaC;

    @Column(name = "horac", nullable = false)
    private LocalTime horaC;

    @Column(name = "id_servicio", nullable = false)
    private Integer idServicio;

    // Getters y Setters
    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getApellidoC() {
        return apellidoC;
    }

    public void setApellidoC(String apellidoC) {
        this.apellidoC = apellidoC;
    }

    public String getTelefonoC() {
        return telefonoC;
    }

    public void setTelefonoC(String telefonoC) {
        this.telefonoC = telefonoC;
    }

    public String getCorreoC() {
        return correoC;
    }

    public void setCorreoC(String correoC) {
        this.correoC = correoC;
    }

    public LocalDate getFechaC() {
        return fechaC;
    }

    public void setFechaC(LocalDate fechaC) {
        this.fechaC = fechaC;
    }

    public LocalTime getHoraC() {
        return horaC;
    }

    public void setHoraC(LocalTime horaC) {
        this.horaC = horaC;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }
}
