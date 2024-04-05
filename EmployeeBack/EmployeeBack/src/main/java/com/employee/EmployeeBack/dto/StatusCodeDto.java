package  com.employee.EmployeeBack.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusCodeDto<T> {

    private int statusCode;
    private String message;
    private T response;
}
