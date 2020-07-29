package com.vk.bot.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
@ConfigurationProperties(prefix = "api")
@Getter
@Setter
public class ApiConfiguration {

	public static final String OK_RESPONSE = "ok";
	public static final String API_VERSION = "5.120";

	@NonNull private String token;
	@NonNull private String confirmation;
}
