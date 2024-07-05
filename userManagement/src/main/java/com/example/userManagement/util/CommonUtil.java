package com.example.userManagement.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.example.userManagement.entity.UsereEntity;
import com.example.userManagement.model.UserDto;

public class CommonUtil {



	public static List<UserDto> convertDtoToEntity(List<UsereEntity> userEntity) {
		if (userEntity.isEmpty()) {
			return null;
		}
		ModelMapper mapper = new ModelMapper();
		System.out.println(userEntity.stream().toString());
		return userEntity.stream().map(userDetails -> mapper.map(userDetails, UserDto.class)).collect(Collectors.toList());
	}

	public static UserDto convertEntityToDto(UsereEntity userEntity) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(userEntity, UserDto.class);
	}



}
