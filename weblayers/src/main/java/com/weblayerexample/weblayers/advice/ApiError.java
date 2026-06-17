package com.weblayerexample.weblayers.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data           //  in short: @Data = @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor
@Builder
public class ApiError
{
    private HttpStatus status;
    private String message;

}
