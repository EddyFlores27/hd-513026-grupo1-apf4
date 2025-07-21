package com.javaapp.veterinaria.Services;

import com.javaapp.veterinaria.DTO.UsuarioRegistroDTO;
import com.javaapp.veterinaria.Model.Usuario;

public interface UsuarioServicio {

    public Usuario save (UsuarioRegistroDTO registroDTO);
    boolean existeCorreo(String correo);
    boolean existeTelefono(String telefono);
    boolean existeDni(String dni);
    void eliminarUsuario(int id); //Metodo para eliminar un usuario
    void actualizarUsuario(Usuario usuario); //Metodo para actualizar un usuario
}
