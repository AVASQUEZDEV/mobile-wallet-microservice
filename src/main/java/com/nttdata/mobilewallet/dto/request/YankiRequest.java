package com.nttdata.mobilewallet.dto.request;

import lombok.Data;

/**
 * This class defines the request of mobile wallet
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
public class YankiRequest {

    private String associatedTo;

    private String documentType;

    private String documentNumber;

    private String phone;

    private String imei;

    private String email;

}
