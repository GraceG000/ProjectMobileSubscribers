package com.gracegh.ProjectMobileSubscribers.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity//to enable this class to interact with the jpa database...
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscribers")
public class Subscriber {

    @Id//to make the subscriber id a primary key...
    @SequenceGenerator(
            name = "subscriber_sequence",
            sequenceName = "subscriber_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.AUTO)//for generating the primary key...
    //setting the field of the class...
    @Column(name="id")
    private Integer id;
    @Column(name="msisdn", unique = true)
    private String msisdn = "";
    @Column(name = "customer_id_owner",unique = true)
    private Integer customerIdOwner;
    @Column(name = "customer_id_user", unique = true)//we use the @Column annotation because we will be performing a native query...
    private Integer customerIdUser;
    @Enumerated(EnumType.STRING)
    @Column(name = "service_type")
    private ServiceType serviceType;
    @Column(name = "service_start_date")
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private String serviceStartDate;


}

