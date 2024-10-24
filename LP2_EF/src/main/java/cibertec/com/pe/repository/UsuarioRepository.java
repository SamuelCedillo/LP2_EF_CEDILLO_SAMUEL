package cibertec.com.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cibertec.com.pe.model.UsuarioEntity;
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String>{
	UsuarioEntity findByCorreo(String correo);
}
