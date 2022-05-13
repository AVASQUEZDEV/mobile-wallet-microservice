package com.nttdata.mobilewallet.model;

import com.nttdata.mobilewallet.enums.BuySaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * This class defines the model of peer to peer
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "peer_to_peer")
public class P2P {

    @Id
    private String id;

    @Field(name = "document_type", write = Field.Write.NON_NULL)
    private String documentType;

    @Field(name = "document_number", write = Field.Write.NON_NULL)
    private String documentNumber;

    @Field(name = "phone", write = Field.Write.NON_NULL)
    private String phone;

    @Field(name = "email", write = Field.Write.NON_NULL)
    private String email;

    @Field(name = "buy_status")
    private BuySaleStatus buyStatus;

    @Field(name = "sale_status")
    private BuySaleStatus saleStatus;

    @Field(name = "created_at")
    private Date createdAt;

    @Field(name = "updated_at")
    private Date updatedAt;

    public P2P(String documentType, String documentNumber, String phone, String email,
               BuySaleStatus buyStatus, BuySaleStatus saleStatus, Date createdAt, Date updatedAt) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.email = email;
        this.buyStatus = buyStatus;
        this.saleStatus = saleStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
