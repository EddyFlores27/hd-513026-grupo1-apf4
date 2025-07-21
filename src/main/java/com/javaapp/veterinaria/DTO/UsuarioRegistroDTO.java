package com.javaapp.veterinaria.DTO;

public class UsuarioRegistroDTO {
    private Integer id;
    private String dni;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contra;
    private String repassword;

    //Construtores

    public UsuarioRegistroDTO() {
        super();
    }

    public UsuarioRegistroDTO(String dni) {
        this.dni = dni;
    }

    public UsuarioRegistroDTO(int id, String dni, String nombre, String apellido, String correo, String telefono,
    String contra, String repassword) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.contra = contra;
        this.repassword = repassword; 
    }

    public UsuarioRegistroDTO(String dni, String nombre, String apellido, String correo, String telefono,
            String contra, String repassword) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.contra = contra;
        this.repassword = repassword; 
    }
    
    //Getter y Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
