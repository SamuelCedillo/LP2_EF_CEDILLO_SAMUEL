package cibertec.com.pe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.model.Model;
import cibertec.com.pe.model.UsuarioEntity;
import cibertec.com.pe.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor 
public class UsuarioController {
	private final UsuarioService usuarioService = null;
	
@GetMapping("/registrar_usuario")
public String mostrarRegistrarUsuario(org.springframework.ui.Model model) {
	model.addAttribute("usuario", new UsuarioEntity());
	return "registrar_usuario";
}

@PostMapping("/registrar_usuario")
public String registrarUsuario(@ModelAttribute("usuario") UsuarioEntity usuarioFormulario,
		Model model, @RequestParam("foto") MultipartFile foto) {
	
	usuarioService.crearUsuario(usuarioFormulario, foto);
	return "registrar_usuario";
}

@GetMapping("/")
public String login(org.springframework.ui.Model model) {
    model.addAttribute("usuario",new UsuarioEntity());
    return "login";
}

}
