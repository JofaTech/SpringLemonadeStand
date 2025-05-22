package com.cooksys.lemonadestand.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.LemonadeStand;
import com.cooksys.lemonadestand.exceptions.BadRequestException;
import com.cooksys.lemonadestand.exceptions.NotFoundException;
import com.cooksys.lemonadestand.mappers.LemonadeStandMapper;
import com.cooksys.lemonadestand.model.LemonadeStandRequestDto;
import com.cooksys.lemonadestand.model.LemonadeStandResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeStandRepository;
import com.cooksys.lemonadestand.services.LemonadeStandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LemonadeStandServiceImpl implements LemonadeStandService {

	private final LemonadeStandRepository lemonadeStandRepository;
	private final LemonadeStandMapper lemonadeStandMapper;

	private void validateLemonadeStandRequest(LemonadeStandRequestDto lemonadeStandRequestDto) {
		if (lemonadeStandRequestDto.getName() == null) {
			throw new BadRequestException("All fields are required on a lemonade stand request dto");
		}
	}

	@Override
	public LemonadeStand getLemonadeStand(Long id) {
		Optional<LemonadeStand> optionalLemonadeStand = lemonadeStandRepository.findById(id);
		if (optionalLemonadeStand.isEmpty()) {
			throw new NotFoundException("No lemonade stand found with id: " + id);
		}
		return optionalLemonadeStand.get();
	}

	@Override
	public LemonadeStandResponseDto createLemonadeStand(LemonadeStandRequestDto lemonadeStandRequestDto) {
		validateLemonadeStandRequest(lemonadeStandRequestDto);
		// Converts request DTO to an entity
		// Saves entity to a database, returns new entity with id
		// Converts to a response DTO and sends back to the client
		return lemonadeStandMapper.entityToLemonadeStandResponseDto(
				lemonadeStandRepository.saveAndFlush(lemonadeStandMapper.lemonadeStandRequestDtoToEntity(lemonadeStandRequestDto)));
	}

	@Override
	public List<LemonadeStandResponseDto> getAllLemonadeStands() {
		return lemonadeStandMapper.entitiesToResponseDtos(lemonadeStandRepository.findAll());
	}

	@Override
	public LemonadeStandResponseDto getLemonadeStandById(Long id) {
		return lemonadeStandMapper.entityToLemonadeStandResponseDto(getLemonadeStand(id));
	}

	@Override
	public LemonadeStandResponseDto updateLemonadeStand(Long id, LemonadeStandRequestDto lemonadeStandRequestDto) {
		LemonadeStand lemonadeStandToUpdate = getLemonadeStand(id);
		if (lemonadeStandRequestDto.getName() != null) {
			lemonadeStandToUpdate.setName(lemonadeStandRequestDto.getName());
		}
		return lemonadeStandMapper.entityToLemonadeStandResponseDto(lemonadeStandRepository.saveAndFlush(lemonadeStandToUpdate));
	}

	@Override
	public LemonadeStandResponseDto deleteLemonadeStand(Long id) {
		LemonadeStand lemonadeStandToDelete = getLemonadeStand(id);
		lemonadeStandRepository.delete(lemonadeStandToDelete);
		return lemonadeStandMapper.entityToLemonadeStandResponseDto(lemonadeStandToDelete);
	}
	
}
