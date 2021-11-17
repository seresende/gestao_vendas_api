package com.resoft.gestaovendas.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private long codigo;
	
	@Column(name = "descricao")
	@NotBlank(message = "Descrição")
	@Length(min = 3, max= 100, message = "Descrição" )
	private String descricao;
	
	@Column(name = "quantidade")
	@NotNull(message = "Quantidade")
	private Integer quantidade;
	
	@Column(name = "preco_curto")
	@NotNull(message = "Preço Custo")
	private BigDecimal precoCusto;
	
	@Column(name = "preco_venda")
	@NotNull(message = "Preço Venda")
	private BigDecimal precoVenda;
	
	@Column(name = "observacao")
	@Length(max= 500, message = "Observação")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
	@NotNull(message = "Código Categoria")
	private Categoria categoria;

}
