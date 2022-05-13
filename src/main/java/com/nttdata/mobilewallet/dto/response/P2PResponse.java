package com.nttdata.mobilewallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.mobilewallet.enums.BuySaleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * This class defines the response of P2P
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class P2PResponse {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "documentType")
    private String documentType;

    @JsonProperty(value = "documentNumber")
    private String documentNumber;

    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "buyStatus")
    private BuySaleStatus buyStatus;

    @JsonProperty(value = "saleStatus")
    private BuySaleStatus saleStatus;

    @JsonProperty(value = "createdAt")
    private Date createdAt;

    @JsonProperty(value = "updatedAt")
    private Date updatedAt;

}
