package com.vk.bot.util;

import com.vk.bot.annotations.PropertyName;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.beans.PropertyDescriptor;
import java.util.Optional;

import static java.beans.Introspector.getBeanInfo;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

@Service
public class VkClient {

	private final static String API_TEMPLATE = "https://api.vk.com/method/%s/?PARAMETERS&";
	private static final String PARAM_DELIMITER = "&";

	public <T> T callGet(String methodName,
	                     Object params,
	                     Class<? extends T> responseClass) {
		var url = format(API_TEMPLATE, methodName) + stringifyParams(params);
		return new RestTemplate().getForObject(url, responseClass);
	}

	@SneakyThrows
	private String stringifyParams(Object object) {
		var clazz = object.getClass();
		return stream(getBeanInfo(clazz, Object.class).getPropertyDescriptors())
				.filter(pd -> pd.getReadMethod() != null)
				.map(pd -> stringifyParam(object, pd))
				.collect(joining(PARAM_DELIMITER));
	}

	private String stringifyParam(Object object,
	                              PropertyDescriptor descriptor) {
		return getParamName(descriptor) + "=" + getParamValue(descriptor, object);
	}

	private String getParamName(PropertyDescriptor descriptor) {
		var propertyName = descriptor.getReadMethod().getAnnotation(PropertyName.class);
		return Optional.ofNullable(propertyName)
				.map(PropertyName::value)
				.orElseGet(descriptor::getName);
	}

	@SneakyThrows
	private Object getParamValue(PropertyDescriptor descriptor,
	                             Object object) {
		var getter = descriptor.getReadMethod();
		return getter.invoke(object);
	}
}
