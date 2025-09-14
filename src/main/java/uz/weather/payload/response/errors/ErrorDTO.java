package uz.weather.payload.response.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {

    private int status;
    private String message;
    private String traceId;
    private List<FieldErrorDTO> fieldErrors;

    public ErrorDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorDTO(int status, String message, String traceId) {
        this.status = status;
        this.message = message;
        this.traceId = traceId;
    }
}