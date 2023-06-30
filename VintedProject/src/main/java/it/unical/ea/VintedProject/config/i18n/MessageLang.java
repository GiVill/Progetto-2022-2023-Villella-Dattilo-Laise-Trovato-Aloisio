package it.unical.ea.VintedProject.config.i18n;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageLang {

    private final ResourceBundleMessageSource messageSource;

    //Language is set by the HTTP Request
    //Those two class compare the code in the Resource Bundle 'messages' and the code passed has a String.
    /* Es: messageLang.getMessage(firstname.not.empty) -> will go in the Resource Bundle 'messages' and see if there's any "firstname.not.empty".
       If affirmative will display the relative value
       In this case the output is: Please provide a valid firstname. */
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    /* Es: messageLang.getMessage(user.not.present, 1234) -> will go in the Resource Bundle 'messages' and see if there's any "user.not.present".
       If affirmative will display the relative value.
       In this case the output is: User 1234 not present in DB */
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}