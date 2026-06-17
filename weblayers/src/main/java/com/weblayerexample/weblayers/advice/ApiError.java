package com.weblayerexample.weblayers.advice;

import lombok.Builder;
import lombok.Data;
import org.apache.catalina.LifecycleState;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data           //  in short: @Data = @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor
@Builder
public class ApiError
{
    private HttpStatus status;
    private String message;
    private List<String> subErrors;
}
