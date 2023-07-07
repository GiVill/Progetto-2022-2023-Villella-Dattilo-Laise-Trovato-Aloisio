package it.unical.ea.VintedProject.core.handler;

import it.unical.ea.VintedProject.exception.ManyRequestException;
import it.unical.ea.VintedProject.dto.ServiceError;
import it.unical.ea.VintedProject.exception.UserException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

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

    private ServiceError errorResponse (WebRequest req, String message) {
        HttpServletRequest httpreq = (HttpServletRequest) req.resolveReference("request");
        final ServiceError output = new ServiceError(new Date(), httpreq.getRequestURI(), message);
        log.error("Exception handler :::: {}", output.toString());
        return output;

    }
}