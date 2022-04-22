package me.changwoo.authserver.protocol.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResultResponse {
    private Boolean success;
    private List<?> response;
    private ErrorResponse error;

    @Builder
    public ResultResponse(Boolean success, List<?> response, ErrorResponse error) {
        this.success = success;
        this.response = response;
        this.error = error;
    }
}
