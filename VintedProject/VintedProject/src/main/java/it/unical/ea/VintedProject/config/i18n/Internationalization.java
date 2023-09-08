package it.unical.ea.VintedProject.config.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@Configuration
public class Internationalization {

    //Sets all the language supported by our server
    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {
        final LanguageResolver resolver = new LanguageResolver();
        resolver.setSupportedLocales(Arrays.asList(Locale.ITALY, Locale.US, Locale.UK)); //All the language supported by our server
        resolver.setDefaultLocale(Locale.ITALY); //Default language
        return resolver;
    }

    //Load from the  | resource-> Resource Bundle 'messages' | the relative .properties requested in the HTTP Request (see LanguageResolver class)
    @Bean
    public ResourceBundleMessageSource messageSource() {
        final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("language/messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}