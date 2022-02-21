package com.projetCloud.projetCloudRESTWS.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="confirmationtoken")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @CreationTimestamp
    @Column(name = "dateexpiration")
    private Timestamp dateExpiration;
}
