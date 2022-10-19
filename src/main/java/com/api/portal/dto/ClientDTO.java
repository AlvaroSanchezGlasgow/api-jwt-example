package com.api.portal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
public class ClientDTO {

    private int id;
    private String name;
    private String website;
    private String clientManager;
 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date registrationDate;



}
