package com.gracegh.ProjectMobileSubscribers.Service;

import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubscriberService {


    //adding a new subscriber to the database...
    /*Subscriber addNewSubscriber(SubscriberDTO subscriber);*/

    //adding a new subscriber to the database...
    Subscriber addNewSubscriber(Subscriber subscriber);

    List<Subscriber> getMsisdn();

    Page<Subscriber> getPagination(int pageNo, int pageSize, String sortField, String sortDirection);

}
