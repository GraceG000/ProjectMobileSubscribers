package com.gracegh.ProjectMobileSubscribers.Entity;

import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;

import javax.persistence.*;
import java.sql.Date;

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
    private long serviceId;
    private String msisdn = "";
    @Column(name = "customer_id_owner")
    private int customerIdOwner;
    @Column(name = "customer_id_user")//we use the @Column annotation because we will be performing a native query...
    private int customerIdUser;
    @Column(name = "service_type")
    private ServiceType serviceType;
    @Column(name = "service_start_date")
    private long serviceStartDate = System.currentTimeMillis() / 1000;

    public Subscriber(long serviceId, String msisdn, int customerIdOwner, int customerIdUser, ServiceType serviceType, long serviceStartDate) {
        this.serviceId = serviceId;
        this.msisdn = msisdn;
        this.customerIdOwner = customerIdOwner;
        this.customerIdUser = customerIdUser;
        this.serviceType = serviceType;
        this.serviceStartDate = serviceStartDate;
    }

    public Subscriber() {
    }

    //creating the getters and setters for the class...
    public long getId() {
        return serviceId;
    }

    public void setId(int id) {
        this.serviceId= id;
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

    public long getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(long serviceStartDate) {
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
                "id=" + serviceId +
                ", msisdn='" + msisdn + '\'' +
                ", customerIdOwner=" + customerIdOwner +
                ", customerIdUser=" + customerIdUser +
                ", serviceType=" + serviceType +
                ", serviceStartDate=" + serviceStartDate +
                '}';
    }

}

