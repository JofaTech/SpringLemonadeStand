package com.cooksys.lemonadestand.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Lemonade {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private double lemonJuice;

	@Column(nullable = false)
	private double water;

	@Column(nullable = false)
	private double sugar;

	@Column(nullable = false)
	private int iceCubes;

	@Column(nullable = false)
	private double price;

	@ManyToOne
	@JoinColumn
	private Order order;

}
