package cibertec.com.pe.model;

import java.sql.Date;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "tb_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer productoId;
	
	@Column(name = "nombre_producto", nullable = false)
	private String nombreProducto;
	
	
	@Column(name = "precio_producto", nullable = false)
	private Double precioProducto;
	
	@Column(name = "stock", nullable = false)
	private Integer stock;
	
	
	@Column(name = "categoria")
	private String categoria;
	
	@Column(name = "url_imagen")
	private String urlImagen;

	public ProductoEntity(Integer productoId, String nombreProducto, Double precioProducto, Integer stock,
			String categoria, String urlImagen) {
		super();
		this.productoId = productoId;
		this.nombreProducto = nombreProducto;
		this.precioProducto = precioProducto;
		this.stock = stock;
		this.categoria = categoria;
		this.urlImagen = urlImagen;
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(Double precioProducto) {
		this.precioProducto = precioProducto;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public ProductoEntity() {
		super();
	}
	
	
}
