package com.gracegh.ProjectMobileSubscribers.Service;

import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SubscriberService {


    //adding a new subscriber to the database...
    /*Subscriber addNewSubscriber(SubscriberDTO subscriber);*/

    //adding a new subscriber to the database...#
    Subscriber addNewSubscriber2(Subscriber subscriber);
    Subscriber addNewSubscriber(Subscriber subscriber);

    List<Subscriber> getMsisdn();

    Page<Subscriber> getPagination(int pageNo, int pageSize, String sortField, String sortDirection);

    List<Subscriber> getByKeyword(String Keyword);

   List<Subscriber> findSubscriberById(Integer id);
}
