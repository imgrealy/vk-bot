package com.vk.bot.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackObject {

	private final CallbackUserMessage callbackUserMessage;

	@JsonCreator
	public CallbackObject(@JsonProperty("message") CallbackUserMessage callbackUserMessage) {
		this.callbackUserMessage = callbackUserMessage;
	}

	public CallbackUserMessage getUserMessage() {
		return callbackUserMessage;
	}
}
