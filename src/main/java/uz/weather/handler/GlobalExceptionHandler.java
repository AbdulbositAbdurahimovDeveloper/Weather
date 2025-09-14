package uz.weather.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import uz.weather.payload.response.ResponseDTO;
import uz.weather.payload.response.errors.ErrorDTO;
import uz.weather.payload.response.errors.FieldErrorDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * A global exception handler that intercepts exceptions thrown by controllers.
 * This class ensures that all API responses, including errors, follow a consistent,
 * structured JSON format (wrapped in {@link ResponseDTO}). This approach simplifies
 * client-side error handling and provides clear, actionable feedback.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link NoResourceFoundException}, which is thrown when a static resource
     * (e.g., HTML, CSS, JS file) cannot be found in the configured static content locations.
     * This provides a consistent JSON 404 response instead of the default Spring error page.
     *
     * @param ex The caught {@link NoResourceFoundException}.
     * @return A {@link ResponseEntity} with a 404 Not Found status.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseDTO<Object>> handleNoResourceFoundException(NoResourceFoundException ex) {
        log.warn("Static resource not found: {}", ex.getResourcePath());

        ErrorDTO error = ErrorDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(String.format("The requested static resource '%s' does not exist.", ex.getResourcePath()))
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.error(error));
    }

    /**
     * Handles {@link HttpRequestMethodNotSupportedException}, which occurs when a request is made
     * with an unsupported HTTP method to an existing endpoint.
     *
     * @param ex The caught {@link HttpRequestMethodNotSupportedException}.
     * @return A {@link ResponseEntity} with a 405 Method Not Allowed status, including a message
     *         that lists the allowed HTTP methods for the endpoint.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDTO<Object>> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String supportedMethods = Arrays.toString(Objects.requireNonNull(ex.getSupportedMethods()));
        String message = String.format("Method '%s' is not supported. Supported methods are %s",
                ex.getMethod(), supportedMethods);

        log.warn("HttpRequestMethodNotSupportedException: {}", message);

        ErrorDTO error = ErrorDTO.builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message(message)
                .build();

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ResponseDTO.error(error));
    }

    /**
     * Handles {@link NoHandlerFoundException}, which is thrown when a request is made to a
     * non-existent endpoint. This handler is only active if the following properties are set:
     * {@code spring.mvc.throw-exception-if-no-handler-found=true} and
     * {@code spring.web.resources.add-mappings=false}.
     *
     * @param ex The caught {@link NoHandlerFoundException}.
     * @param request The current HTTP request, used to get the requested URI.
     * @return A {@link ResponseEntity} with a 404 Not Found status.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseDTO<Object>> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        log.warn("No handler found for {} {}", ex.getHttpMethod(), ex.getRequestURL());

        ErrorDTO error = ErrorDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(String.format("The requested resource '%s' could not be found.", request.getRequestURI()))
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.error(error));
    }

    /**
     * Handles {@link MethodArgumentNotValidException}, which is thrown when DTO validation fails
     * (e.g., due to {@code @Valid} annotation on a controller method parameter).
     *
     * @param ex The caught {@link MethodArgumentNotValidException}.
     * @return A {@link ResponseEntity} with a 400 Bad Request status, including a list of
     *         field-specific validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldErrorDTO> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new FieldErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorDTO error = ErrorDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed. Please check your input.")
                .fieldErrors(fieldErrors)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseDTO.error(error));
    }

    /**
     * A catch-all handler for any other unhandled exceptions, which acts as a safety net.
     * This handler prevents exposing stack traces to the client and provides a traceable error ID.
     *
     * @param ex The generic {@link Exception} that was not caught by more specific handlers.
     * @return A {@link ResponseEntity} with a 500 Internal Server Error status and a generic message
     *         that includes a unique trace ID for debugging purposes.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleAllOtherExceptions(Exception ex) {
        String traceId = UUID.randomUUID().toString();
        log.error("Unhandled exception occurred with traceId: {}", traceId, ex);

        String userMessage = "An unexpected error occurred on the server. Please try again later.";

        ErrorDTO error = ErrorDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(userMessage)
                .traceId(traceId)
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.error(error));
    }
}