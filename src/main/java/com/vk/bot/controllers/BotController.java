package com.vk.bot.controllers;

import com.vk.bot.api.Callback;
import com.vk.bot.util.BotService;
import com.vk.bot.configuration.ApiConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bot")
public class BotController {

	private final ApiConfiguration configuration;

	private final BotService botService;

	public BotController(ApiConfiguration configuration,
	                     BotService botService) {
		this.configuration = configuration;
		this.botService = botService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String listenBot(@RequestBody Callback callback) {
		if (callback.isConfirmation()) {
			return configuration.getConfirmation();
		} else {
			botService.sendReply(callback.getUserMessage());
			return ApiConfiguration.OK_RESPONSE;
		}
	}

}
