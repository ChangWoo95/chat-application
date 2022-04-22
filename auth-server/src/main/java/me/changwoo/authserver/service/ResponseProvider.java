package me.changwoo.authserver.service;

import me.changwoo.authserver.protocol.response.ErrorResponse;
import me.changwoo.authserver.protocol.response.ResultResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseProvider {
    public ResponseEntity<?> successMessage(boolean success, List<Object> data) {
        return new ResponseEntity<>(ResultResponse.builder()
                                                  .success(success)
                                                  .response(data)
                                                  .error(null)
                                                  .build(),
                                    HttpStatus.OK);
    }

    public ResponseEntity<?> successMessage(boolean success, List<Object> data, HttpHeaders headers) {
        return new ResponseEntity<>(ResultResponse.builder()
                .success(success)
                .response(data)
                .error(null)
                .build(),
                headers,
                HttpStatus.OK);
    }

    public ResponseEntity<?> errorMessage(boolean success, String name, String message, HttpStatus status) {
        return new ResponseEntity<>(ResultResponse.builder()
                .success(success)
                .response(null)
                .error(ErrorResponse.builder()
                        .name(name)
                        .message(message)
                        .build()
                )
                .build(),
                status);
    }

}
