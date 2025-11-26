package com.example.cms.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BaseResponse {
    private Integer status_code;
    private Object message;

    @Override
    public String toString() {
        return "{\"status\": " + status_code + ",\n\"message\":\"" + message + "\"}";
    }
}
