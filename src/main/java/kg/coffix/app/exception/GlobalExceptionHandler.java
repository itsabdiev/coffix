package kg.coffix.app.exception;

import io.jsonwebtoken.JwtException;
import kg.coffix.app.dto.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponse usernameNotFoundExceptionHandling(UsernameNotFoundException e) {
        return MessageResponse.builder()
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .statusCode(404)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MessageResponse jwtExceptionHandling(JwtException e) {
        return MessageResponse.builder()
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .statusCode(403)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponse notFoundExceptionHandling(NotFoundException re) {
        return MessageResponse.builder()
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .statusCode(404)
                .message(re.getMessage())
                .build();
    }


}
