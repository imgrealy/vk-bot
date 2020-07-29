package com.vk.bot.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Callback {

	private final static Map<String, Type> ALIASES = stream(Type.values())
			.collect(toMap(Type::getAlias, identity()));

	public static Type getTypeFromAlias(String alias) {
		if (ALIASES.containsKey(alias))
			return ALIASES.get(alias);
		else
			throw new IllegalArgumentException("Отсутствует тип для " + alias);
	}

	private final Type type;
	private final CallbackObject callbackObject;

	@JsonCreator
	public Callback(@JsonProperty("type") String alias,
	                @JsonProperty("object") CallbackObject callbackObject) {
		this.type = getTypeFromAlias(alias);
		this.callbackObject = callbackObject;
	}

	public boolean isConfirmation() {
		return type == Type.CONFIRMATION;
	}

	public CallbackUserMessage getUserMessage() {
		if (type == Type.MESSAGE) {
			return callbackObject.getUserMessage();
		} else {
			throw new IllegalStateException("Данный вызов не является вызовом сообщения");
		}
	}

	public enum Type {

		CONFIRMATION("confirmation"),
		MESSAGE("message_new");

		@Getter private final String alias;

		Type(String alias) {
			this.alias = alias;
		}
	}
}
