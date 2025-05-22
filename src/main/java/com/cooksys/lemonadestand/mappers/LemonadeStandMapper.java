package com.cooksys.lemonadestand.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.lemonadestand.entities.LemonadeStand;
import com.cooksys.lemonadestand.model.LemonadeStandDto;
import com.cooksys.lemonadestand.model.LemonadeStandRequestDto;
import com.cooksys.lemonadestand.model.LemonadeStandResponseDto;

@Mapper(componentModel = "spring")
public interface LemonadeStandMapper {

	LemonadeStand lemonadeStandRequestDtoToEntity(LemonadeStandRequestDto lemonadeStandRequestDto);

	LemonadeStand lemonadeStandDtoToEntity(LemonadeStandDto lemonadeStandDto);

	LemonadeStandResponseDto entityToLemonadeStandResponseDto(LemonadeStand lemonadeStand);

	List<LemonadeStandResponseDto> entitiesToResponseDtos(List<LemonadeStand> lemonadeStands);

}
