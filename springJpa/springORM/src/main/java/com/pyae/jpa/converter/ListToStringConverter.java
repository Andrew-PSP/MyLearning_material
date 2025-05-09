package com.pyae.jpa.converter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.AttributeConverter;

public class ListToStringConverter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		
		return Optional.ofNullable(attribute).map(list-> list.stream().collect(Collectors.joining(","))).orElse(null);
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		if(StringUtils.hasLength(dbData)) {
			var result = dbData.split(",");
			return Arrays.asList(result);
		}
		return null;
	}

}
