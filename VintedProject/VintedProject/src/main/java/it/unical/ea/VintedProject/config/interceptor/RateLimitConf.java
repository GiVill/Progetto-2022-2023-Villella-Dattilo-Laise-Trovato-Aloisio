package it.unical.ea.VintedProject.config.interceptor;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RateLimitConf { } // Serve per abilitare il chacing con @EnableCaching
