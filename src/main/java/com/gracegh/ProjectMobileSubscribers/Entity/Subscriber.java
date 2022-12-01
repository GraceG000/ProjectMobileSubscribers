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
    @Column(name="msisdn")
    private String msisdn = "";
    @Column(name = "customer_id_owner")
    private Integer customerIdOwner;
    @Column(name = "customer_id_user")//we use the @Column annotation because we will be performing a native query...
    private Integer customerIdUser;
    @Enumerated(EnumType.STRING)
    @Column(name = "service_type")
    private ServiceType serviceType;
    @Column(name = "service_start_date")
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private String serviceStartDate;

 /*   public Subscriber(Integer id, String msisdn, Integer customerIdOwner, Integer customerIdUser, ServiceType serviceType, LocalDateTime serviceStartDate) {
        this.id = id;
        this.msisdn = msisdn;
        this.customerIdOwner = customerIdOwner;
        this.customerIdUser = customerIdUser;
        this.serviceType = serviceType;
        this.serviceStartDate = serviceStartDate;
    }

    public Subscriber() {
    }

    //creating the getters and setters for the class...
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id= id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Integer getCustomerIdOwner() {
        return customerIdOwner;
    }

    public void setCustomerIdOwner(Integer customerIdOwner) {
        this.customerIdOwner = customerIdOwner;
    }

    public Integer getCustomerIdUser() {
        return customerIdUser;
    }

    public void setCustomerIdUser(Integer customerIdUser) {
        this.customerIdUser = customerIdUser;
    }

    public LocalDateTime getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(LocalDateTime serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", msisdn='" + msisdn + '\'' +
                ", customerIdOwner=" + customerIdOwner +
                ", customerIdUser=" + customerIdUser +
                ", serviceType=" + serviceType +
                ", serviceStartDate=" + serviceStartDate +
                '}';
    }*/

}

