package com.cooksys.lemonadestand.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.services.LemonadeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LemonadeServiceImpl implements LemonadeService {

	private LemonadeRepository lemonadeRepository;
	
	@Override
	public List<LemonadeResponseDto> getAllLemonades() {
		List<LemonadeResponseDto> result = new ArrayList<LemonadeResponseDto>();
		for (Lemonade lemonade : lemonadeRepository.findAll()) {
			result.add(new LemonadeResponseDto(lemonade.getId(), lemonade.getPrice()));
		}
 		return result;
	}

	@Override
	public LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto) {
		
		// Map the request DTO to the lemonade entity
		Lemonade lemonadeToSave = new Lemonade();
		lemonadeToSave.setLemonJuice(lemonadeRequestDto.getLemonJuice());
		lemonadeToSave.setWater(lemonadeRequestDto.getWater());
		lemonadeToSave.setSugar(lemonadeRequestDto.getSugar());
		lemonadeToSave.setIceCubes(lemonadeRequestDto.getIceCubes());
		lemonadeToSave.setPrice(lemonadeToSave.getLemonJuice() * 0.20 + lemonadeToSave.getWater() * 0.01 + 
				lemonadeToSave.getSugar() * 0.15 + lemonadeToSave.getIceCubes() * 0.05 + 0.50);
		
		// Save the new lemonade entity and store the resulting entity with the ID generated
		// from the database in a variable named SavedLemonade
		Lemonade savedLemonade = lemonadeRepository.saveAndFlush(lemonadeToSave);
		
		// Map the newly saved entity with the generated id to a response DTO and return it
		return new LemonadeResponseDto(savedLemonade.getId(), savedLemonade.getPrice());
	}

}
