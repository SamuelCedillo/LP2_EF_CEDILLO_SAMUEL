package cibertec.com.pe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cibertec.com.pe.model.UsuarioEntity;
import cibertec.com.pe.repository.UsuarioRepository;
import cibertec.com.pe.service.UsuarioService;
import cibertec.com.pe.utils.Utilitarios;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void crearUsuario(UsuarioEntity usuarioEntity, MultipartFile foto) {
		String nombreFoto = Utilitarios.guardarImagen(foto);
	}

	@Override
	public boolean validarUsuario(UsuarioEntity usuarioEntity) {
		// TODO Auto-generated method stub
		return false;
	}

}
