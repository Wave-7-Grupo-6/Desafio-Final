package br.com.dh.meli.desafiofinal.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getFieldErrors();
        Map<String, String> fieldErrors = new HashMap<>();
        errors.forEach(
                err -> fieldErrors.put(err.getField(), err.getDefaultMessage())
        );

        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Parâmetros inválidos")
                        .message("Um ou mais campos com valor inválido.")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .fieldErrors(fieldErrors)
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDetails> handleDataIntegrityViolationException (DataIntegrityViolationException ex){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Dados inválidos")
                        .message("Um ou mais valores fornecidos são inválidos")
                        .status(HttpStatus.BAD_GATEWAY.value())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Objeto não encontrado")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSpaceAvailableException.class)
    public ResponseEntity<ExceptionDetails> handlerNoSpaceAvailableException(NoSpaceAvailableException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("No space avaliable")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCompatibleException.class)
    public ResponseEntity<ExceptionDetails> handlerNoCompatibleException(NoCompatibleException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("No compatible")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionDetails> handlerNoSuchElementException(NoSuchElementException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Object not found")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ExceptionDetails> handlerOutOfStockException(OutOfStockException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Object out of stock")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
