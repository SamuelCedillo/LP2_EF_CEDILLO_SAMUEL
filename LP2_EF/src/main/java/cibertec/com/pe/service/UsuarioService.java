package cibertec.com.pe.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cibertec.com.pe.model.UsuarioEntity;
import lombok.RequiredArgsConstructor;
@Service
public interface UsuarioService {

	void crearUsuario(UsuarioEntity usuarioEntity, MultipartFile foto);
	boolean validarUsuario(UsuarioEntity usuarioEntity);
	
}
