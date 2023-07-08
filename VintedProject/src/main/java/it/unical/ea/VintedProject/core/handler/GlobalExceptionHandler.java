package it.unical.ea.VintedProject.core.handler;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.exception.ManyRequestException;
import it.unical.ea.VintedProject.dto.ServiceError;
import it.unical.ea.VintedProject.exception.UserException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.util.Date;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final MessageLang messageLang;

    //404
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceError onResourceNotFoundException(WebRequest req, EntityNotFoundException ex){
        return errorResponse(req, ex.getMessage());
    }

    //401
    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceError onResourceNotFoundException(WebRequest req, UserException ex){
        return errorResponse(req, ex.getMessage() );
    }

    //403
    @ExceptionHandler(ManyRequestException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ServiceError onResourceNotFoundException(WebRequest req, ManyRequestException ex){
        return errorResponse(req, ex.getMessage() );
    }

    //413
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ServiceError handleMaxUploadSizeExceeded(WebRequest req, MaxUploadSizeExceededException ex) {
        return errorResponse(req, messageLang.getMessage("image.dimension.upload.exceeded") );
    }

    /*
    Non necessaria
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ServiceError handleMultipartException(WebRequest req, MultipartException ex) {
        return errorResponse(req, messageLang.getMessage("image.dimension.download.limit"));
    }
     */

    private ServiceError errorResponse (WebRequest req, String message) {
        HttpServletRequest httpreq = (HttpServletRequest) req.resolveReference("request");
        final ServiceError output = new ServiceError(new Date(), httpreq.getRequestURI(), message);
        log.error("Exception handler :::: {}", output.toString());
        return output;

    }
}
