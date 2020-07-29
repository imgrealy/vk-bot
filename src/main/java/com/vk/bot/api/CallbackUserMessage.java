package com.vk.bot.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackUserMessage {

	@Getter private final int userId;

	@Getter private final String message;

	@JsonCreator
	public CallbackUserMessage(@JsonProperty("from_id") int userId,
	                           @JsonProperty("text") String message) {
		this.userId = userId;
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o instanceof CallbackUserMessage) {
			var that = (CallbackUserMessage) o;
			return getUserId() == that.getUserId();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserId());
	}
}
