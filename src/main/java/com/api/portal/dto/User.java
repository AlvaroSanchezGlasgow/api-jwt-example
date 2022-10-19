package com.api.portal.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class User {

   
    private int id;
    private String username;
    private String password;
    private String passwordConfirmation;
    private String firstName;
    private String lastName;
    private String email;
    private String comment;
    private String isActive;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date registrationDate;

}
