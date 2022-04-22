package me.changwoo.authserver.protocol.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String name;
    private  String message;

    @Builder
    public ErrorResponse(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
