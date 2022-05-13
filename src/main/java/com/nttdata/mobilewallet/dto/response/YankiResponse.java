package com.nttdata.mobilewallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * This class defines the response of mobile wallet
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class YankiResponse {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "associatedTo")
    private String associatedTo;

    @JsonProperty(value = "documentType")
    private String documentType;

    @JsonProperty(value = "documentNumber")
    private String documentNumber;

    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "imei")
    private String imei;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "balance")
    private Float balance;

    @JsonProperty(value = "createdAt")
    private Date createdAt;

    @JsonProperty(value = "updatedAt")
    private Date updatedAt;

}
