package com.nttdata.mobilewallet.dto.request;

import com.nttdata.mobilewallet.enums.BuySaleStatus;
import lombok.Data;

/**
 * This class defines to request of model P2P
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
public class P2PRequest {

    private String documentType;

    private String documentNumber;

    private String phone;

    private String email;

    private BuySaleStatus buyStatus;

    private BuySaleStatus saleStatus;

}
