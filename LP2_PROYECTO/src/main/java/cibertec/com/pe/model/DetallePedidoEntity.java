package cibertec.com.pe.model;

import java.util.List;

import org.hibernate.engine.jdbc.env.spi.IdentifierCaseStrategy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "tb_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detalle_id")
	private Integer detalleId;
	
	@Column(name="cantidad", nullable = false)
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(name ="fk_producto", nullable=false)
	private ProductoEntity productoEntity;
	
	@ManyToOne
	@JoinColumn(name = "fk_pedido",nullable = false)
	private PedidoEntity pedidoEntity;
	
	
	@OneToMany(mappedBy = "pedidoEntity", cascade = CascadeType.ALL)
	private List<DetallePedidoEntity>detallePedido;


	public Integer getDetalleId() {
		return detalleId;
	}


	public void setDetalleId(Integer detalleId) {
		this.detalleId = detalleId;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public ProductoEntity getProductoEntity() {
		return productoEntity;
	}


	public void setProductoEntity(ProductoEntity productoEntity) {
		this.productoEntity = productoEntity;
	}


	public PedidoEntity getPedidoEntity() {
		return pedidoEntity;
	}


	public void setPedidoEntity(PedidoEntity pedidoEntity) {
		this.pedidoEntity = pedidoEntity;
	}


	public List<DetallePedidoEntity> getDetallePedido() {
		return detallePedido;
	}


	public void setDetallePedido(List<DetallePedidoEntity> detallePedido) {
		this.detallePedido = detallePedido;
	}


	public DetallePedidoEntity() {
		super();
	}
	
	
}
