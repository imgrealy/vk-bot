package com.vk.bot.api;

import com.vk.bot.annotations.PropertyName;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
public class SendMessageParams {

	@Getter private final String message;
	private final int userId;
	private final String token;
	private final String version;
	private final int id;

	@PropertyName("peer_id")
	public int getUserId() {
		return userId;
	}

	@PropertyName("access_token")
	public String getToken() {
		return token;
	}

	@PropertyName("v")
	public String getVersion() {
		return version;
	}

	@PropertyName("random_id")
	public int getId() {
		return id;
	}
}
