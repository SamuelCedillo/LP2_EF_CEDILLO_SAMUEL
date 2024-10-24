package cibertec.com.pe.controller;

import java.util.List;
import java.util.jar.Attributes.Name;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import cibertec.com.pe.model.ProductoEntity;
import cibertec.com.pe.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.engine.AttributeName;

@Controller
@RequiredArgsConstructor
public class ProductoController {
	private final ProductoService productoService;
	
	public ProductoService getProductoService() {
		return productoService;
	}

	public ProductoController(ProductoService productoService) {
		super();
		this.productoService = productoService;
	}

	@GetMapping("/menu")
	public String mostrarMenu(Model model, HttpSession session) {
		if (session.getAttribute("usuario") == null) {
			return "redirect:/";
		}
		
		List<ProductoEntity>listarProductos = productoService.buscarTodosProductos();
		model.addAttribute("producto", listarProductos);
		return "/menu";
	}
	
}
