package com.vk.bot.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.function.Function;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendMessageResponse {

	private final Object error;

	@JsonCreator
	public SendMessageResponse(@JsonProperty("error") Object error) {
		this.error = error;
	}

	public void throwErrorIfPresent(Function<String, ? extends RuntimeException> function) {
		if (error != null) {
			throw function.apply(error.toString());
		}
	}
}
