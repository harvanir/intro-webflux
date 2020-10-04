package org.harvanir.demo.introwebflux.webmvc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/** @author Harvan Irsyadi */
@EnableJpaAuditing
@Configuration(proxyBeanMethods = false)
public class AppConfiguration {}
