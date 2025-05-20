package com.cooksys.lemonadestand.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.mappers.LemonadeMapper;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.services.LemonadeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LemonadeServiceImpl implements LemonadeService {

	private LemonadeRepository lemonadeRepository;
	private LemonadeMapper lemonadeMapper;
	
	@Override
	public List<LemonadeResponseDto> getAllLemonades() {
		return lemonadeMapper.entitiesToResponseDtos(lemonadeRepository.findAll());
	}

	@Override
	public LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto) {	
		// Map the request DTO to the lemonade entity
		Lemonade lemonadeToSave = lemonadeMapper.requestDtoToEntity(lemonadeRequestDto);
		lemonadeToSave.setPrice(lemonadeToSave.getLemonJuice() * 0.20 + lemonadeToSave.getWater() * 0.01 + 
				lemonadeToSave.getSugar() * 0.15 + lemonadeToSave.getIceCubes() * 0.05 + 0.50);
		
		// Save the new lemonade entity
		// Map the newly saved entity with the generated id to a response DTO and return it
		return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonadeToSave));
	}

	@Override
	public LemonadeResponseDto getLemonadeById(Long id) {
		return lemonadeMapper.entityToResponseDto(lemonadeRepository.getReferenceById(id));
	}

}
