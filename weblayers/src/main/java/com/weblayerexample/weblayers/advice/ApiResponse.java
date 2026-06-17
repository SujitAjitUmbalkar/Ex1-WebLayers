package com.weblayerexample.weblayers.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({ "timeStamp", "data", "error" })
public class ApiResponse<T>
{
    @JsonFormat(pattern = "hh:mm:ss dd - MM - yyyy")
    private LocalDateTime timeStamp;

    private T data;

    private ApiError error;

    public ApiResponse()
    {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data)
    {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError)
    {
        this();
        this.error = apiError;
    }
}
