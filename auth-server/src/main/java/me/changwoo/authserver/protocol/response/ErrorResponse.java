package me.changwoo.authserver.protocol.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {
    private String name;
    private  String message;

    @Builder
    public ErrorResponse(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
