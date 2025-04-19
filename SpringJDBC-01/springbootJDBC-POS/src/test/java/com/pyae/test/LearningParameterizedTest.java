package com.pyae.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.boot.test.context.SpringBootTest;

import com.pyae.pos.PosApplication;
import com.pyae.pos.input.ShoppingCart;
import com.pyae.test.data.ShoppingCartProvider;

@SpringBootTest(classes =PosApplication.class)
public class LearningParameterizedTest {

	enum Day{
		 MONDAY,
		    TUESDAY,
		    WEDNESDAY,
		    THURSDAY,
		    FRIDAY,
		    SATURDAY,
		    SUNDAY
	}
	
	@ParameterizedTest
	@NullSource
	void using_null_source(LocalDateTime date) {
		assertNull(date);
	}
	
	@ParameterizedTest
	@EmptySource
	void using_empty_source(String text) {
		assertEquals(0, text.length());
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	void using_null_empty_source(List<String> list) {
		if(null == list) {
			assertNull(list);
		}else
			assertEquals(0, list.size());
	}
	
	@ParameterizedTest
	@EnumSource(
			value = Day.class,
			names = {
					"SUNDAY", "SATURDAY",
				},
				mode = Mode.EXCLUDE
			)
	void using_enum_source(Day input) {
		System.out.println(input);
	}
	
	
	//you can drop name if the method is only one
	@ParameterizedTest
	@MethodSource("using_method_source")
	void using_method_source(String input) {
		System.out.println(input);
	}
	
	@ParameterizedTest
	@MethodSource("com.pyae.test.data.ShoppingCartProvider#provide")
	void using_method_source_from_external(ShoppingCart cart) {
		System.out.println(cart);
	}
	
	@ParameterizedTest
	@ArgumentsSource(value = ShoppingCartProvider.class)
	void using_argument_source(ShoppingCart cart) {
		System.out.println(cart);
	}
	
	static Stream<String> using_method_source() {
		return Stream.iterate('A', a -> (char)(a.charValue()+1)).limit(5).map(a -> a.toString());
	}
}
