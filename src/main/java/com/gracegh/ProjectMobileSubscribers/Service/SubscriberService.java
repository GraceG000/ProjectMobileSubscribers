package com.gracegh.ProjectMobileSubscribers.Service;

import com.gracegh.ProjectMobileSubscribers.DTO.SubscriberDTO;
import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;

import java.util.List;

public interface SubscriberService {


    //adding a new subscriber to the database...
    /*Subscriber addNewSubscriber(SubscriberDTO subscriber);*/

    //adding a new subscriber to the database...
    Subscriber addNewSubscriber(Subscriber subscriber);

    List<Subscriber> getMsisdn();

}
