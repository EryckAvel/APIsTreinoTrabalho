package br.com.erudio.apigateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@Getter
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

    private Date timestapm;
    private String message;
    private String details;

}
