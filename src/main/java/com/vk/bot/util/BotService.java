package com.vk.bot.util;

import com.vk.bot.api.SendMessageParams;
import com.vk.bot.api.SendMessageResponse;
import com.vk.bot.api.CallbackUserMessage;
import com.vk.bot.configuration.ApiConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.vk.bot.configuration.ApiConfiguration.API_VERSION;
import static java.lang.String.format;

@Service
public class BotService {

	private static final String REPLY_FORMAT = "Вы сказали: %s";
	private static final String SEND_METHOD = "messages.send";
	private final ApiConfiguration configuration;
	private final VkClient vkClient;


	public BotService(ApiConfiguration configuration,
	                  VkClient vkClient) {
		this.configuration = configuration;
		this.vkClient = vkClient;
	}

	public void sendReply(CallbackUserMessage message) {
		var reply = SendMessageParams.builder()
				.token(configuration.getToken())
				.message(format(REPLY_FORMAT, message.getMessage()))
				.version(API_VERSION)
				.userId(message.getUserId())
				.id(0)
				.build();
		var response = vkClient.callGet(SEND_METHOD, reply, SendMessageResponse.class);
		response.throwErrorIfPresent(IllegalStateException::new);
	}
}
