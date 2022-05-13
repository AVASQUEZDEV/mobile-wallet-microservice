package com.nttdata.mobilewallet.dto.response.proxy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.mobilewallet.enums.PayMode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * This class defines the response of say sale rate
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class BuyResponse {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "transactionId")
    private String transactionId;

    @JsonProperty(value = "fromUserId")
    private String fromUserId;

    @JsonProperty(value = "amount")
    private Float amount;

    @JsonProperty(value = "payMode")
    private PayMode payMode;

    @JsonProperty(value = "created_at")
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    private Date updatedAt;

}
