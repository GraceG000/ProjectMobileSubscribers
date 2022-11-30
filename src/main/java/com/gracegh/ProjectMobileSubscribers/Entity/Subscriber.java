package com.gracegh.ProjectMobileSubscribers.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity//to enable this class to interact with the jpa database...
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
    private int id;
    @Column(name="msisdn")
    private String msisdn = "";
    @Column(name = "customer_id_owner")
    private int customerIdOwner;
    @Column(name = "customer_id_user")//we use the @Column annotation because we will be performing a native query...
    private int customerIdUser;
    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false)
    private ServiceType serviceType;
    @Column(name = "service_start_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime serviceStartDate;

    public Subscriber(int id, String msisdn, int customerIdOwner, int customerIdUser, ServiceType serviceType, LocalDateTime serviceStartDate) {
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getCustomerIdOwner() {
        return customerIdOwner;
    }

    public void setCustomerIdOwner(int customerIdOwner) {
        this.customerIdOwner = customerIdOwner;
    }

    public int getCustomerIdUser() {
        return customerIdUser;
    }

    public void setCustomerIdUser(int customerIdUser) {
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
    }

}

