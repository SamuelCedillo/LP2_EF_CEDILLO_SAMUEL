package cibertec.com.pe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;
import cibertec.com.pe.model.UsuarioEntity;
import cibertec.com.pe.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor 
public class UsuarioController {
	private final UsuarioService usuarioService = null;
	
@GetMapping("registrar_usuario")
public String mostrarRegistrarUsuario(org.springframework.ui.Model model) {
	model.addAttribute("usuario", new UsuarioEntity());
	return "registrar_usuario";
}
}
