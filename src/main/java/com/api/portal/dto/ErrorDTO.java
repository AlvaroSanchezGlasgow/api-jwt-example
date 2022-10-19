package com.api.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private Date errorDate;
    private String message;
    private String code;

}