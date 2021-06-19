package com.mx.ivanapi.empleosapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.ivan.config.PageSerializer;

@Configuration
public class Configuracion {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	/*@Bean
	public Module jacksonPageWithJsonViewModule() {
		SimpleModule module = new SimpleModule("jackson-page-with-jsonview", Version.unknownVersion());
		module.addSerializer(PageImpl.class, new PageSerializer());
		return module;
	}*/
}
