package cibertec.com.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.com.pe.model.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String>{
	UsuarioEntity findByCorreo(String correo);
}
