package com.cooksys.lemonadestand.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.services.LemonadeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LemonadeServiceImpl implements LemonadeService {

	private LemonadeRepository lemonadeRepository;
	
	@Override
	public List<Lemonade> getAllLemonades() {
		return lemonadeRepository.findAll();
	}

	@Override
	public Lemonade createLemonade(Lemonade lemonade) {
		lemonade.setPrice(lemonade.getLemonJuice() * 0.20 + lemonade.getWater() * 0.01 + 
				lemonade.getSugar() * 0.15 + lemonade.getIceCubes() * 0.05 + 0.50);
		return lemonadeRepository.saveAndFlush(lemonade);
	}

}
