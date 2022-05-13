package com.nttdata.mobilewallet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * This class defines the model of yanki
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "yanki")
public class Yanki {

    @Id
    private String id;

    @Field(name = "associated_to", write = Field.Write.NON_NULL)
    private String associatedTo;

    @Field(name = "document_type", write = Field.Write.NON_NULL)
    private String documentType;

    @Field(name = "document_number", write = Field.Write.NON_NULL)
    private String documentNumber;

    @Field(name = "phone", write = Field.Write.NON_NULL)
    private String phone;

    @Field(name = "imei", write = Field.Write.NON_NULL)
    private String imei;

    @Field(name = "email", write = Field.Write.NON_NULL)
    private String email;

    @Field(name = "balance", write = Field.Write.NON_NULL)
    private Float balance;

    @Field(name = "created_at")
    private Date createdAt;

    @Field(name = "updated_at")
    private Date updatedAt;

    public Yanki(String associatedTo, String documentType, String documentNumber, String phone,
                 String imei, String email, Date createdAt, Date updatedAt) {
        this.associatedTo = associatedTo;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.imei = imei;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
