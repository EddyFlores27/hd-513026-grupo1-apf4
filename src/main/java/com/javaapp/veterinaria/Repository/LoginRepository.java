package com.javaapp.veterinaria.Repository;
import com.javaapp.veterinaria.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByCorreo(String correo);
}
