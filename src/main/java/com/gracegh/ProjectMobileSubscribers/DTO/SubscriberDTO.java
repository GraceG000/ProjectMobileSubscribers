package com.gracegh.ProjectMobileSubscribers.DTO;
import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriberDTO {

    private int id;

    private String msisdn = "";

    private int customerIdOwner;

    private int customerIdUser;

    private ServiceType serviceType;

    private LocalDateTime serviceStartDate;

}
