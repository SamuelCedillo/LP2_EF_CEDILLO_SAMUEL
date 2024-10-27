package cibertec.com.pe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cibertec.com.pe.model.ProductoEntity;
import cibertec.com.pe.repository.ProductoRepository;
import cibertec.com.pe.service.ProductoService;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

	private final ProductoRepository productoRepository;
	
	public ProductoRepository getProductoRepository() {
		return productoRepository;
	}

	public ProductoServiceImpl(ProductoRepository productoRepository) {
		super();
		this.productoRepository = productoRepository;
	}

	@Override
	public List<ProductoEntity> buscarTodosProductos() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public ProductoEntity buscarProductosPorId(Integer id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id).get();
	}

	
}
