package com.pyae.test.data;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import com.pyae.domain.ProductDetail;

public class ArgumentDetailAggregator implements ArgumentsAggregator {

	@Override
	public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
			throws ArgumentsAggregationException {
		
		return new ProductDetail(accessor.getInteger(0), accessor.getString(1), accessor.getString(2), accessor.getString(3), accessor.getInteger(4));
	}

}
