package com.cooksys.lemonadestand.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="order_table")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private double total;
	
	@OneToMany(mappedBy = "order")
	private List<Lemonade> lemonades;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
	
	@ManyToOne
	@JoinColumn
	private LemonadeStand lemonadeStand;
	
}
