package com.livrariasaara.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
		@JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false,
					foreignKey = @ForeignKey (name = "fk_item_pedido"))
		@ManyToOne(optional=false, fetch = FetchType.LAZY)
		@NotNull
		protected Pedido pedido;

		@NotNull
		@Column(unique = true)
		protected Short item;

		@ManyToOne
		@JoinColumn(name="livro_id", referencedColumnName="id", nullable = false)
		@NotNull
		protected Livro livro;

		@PositiveOrZero
	    @NotNull
	    @Column(nullable = false)
		protected BigDecimal quantidade;

		@Positive
	    @NotNull
	    @Column(name = "preco_unitario", nullable = false)
		protected BigDecimal precoUnitario;
		
		@PositiveOrZero
		@Column(columnDefinition = "NUMERIC(10,2) default 0")
		protected BigDecimal precoTotal;
}
