package hackacode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hackacode.model.payload.MensajeResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MensajeResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(MensajeResponse.builder()
                        .mensaje(ex.getMessage())
                        .objeto(null)
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeResponse> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MensajeResponse.builder()
                        .mensaje("Error inesperado: " + ex.getMessage())
                        .objeto(null)
                        .build());
    }
}
