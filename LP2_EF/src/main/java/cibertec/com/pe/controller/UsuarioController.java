package cibertec.com.pe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import cibertec.com.pe.model.UsuarioEntity;
import cibertec.com.pe.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	@GetMapping("/registrar_usuario")
    public String mostrarRegistrarUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioEntity());
        return "registrar_usuario";
    }

    @PostMapping("/registrar_usuario")
    public String registrarUsuario(@ModelAttribute("usuario") UsuarioEntity usuarioFormulario, Model model,
            @RequestParam("foto") MultipartFile foto) {
            usuarioService.crearUsuario(usuarioFormulario, foto);
            
        return "registrar_usuario";
    }
    
    
    
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("usuario", new UsuarioEntity());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("usuario") UsuarioEntity usuarioFormulario, Model model,
            HttpSession session) {
        Boolean validarUsuario = usuarioService.validarUsuario(usuarioFormulario);
        if (validarUsuario) {
            session.setAttribute("usuario", usuarioFormulario.getCorreo());
            return "redirect:/menu";
        }
        model.addAttribute("error", "Login inválido: no existe el usuario.");
        model.addAttribute("usuario", new UsuarioEntity());
        return "login";
    }
    
    @GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
