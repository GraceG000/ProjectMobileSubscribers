package com.gracegh.ProjectMobileSubscribers.Service;

import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubscriberService {


    //adding a new subscriber to the database...
    /*Subscriber addNewSubscriber(SubscriberDTO subscriber);*/

    //adding a new subscriber to the database...#
    Subscriber addNewSubscriber2(Subscriber subscriber);
    Subscriber addNewSubscriber(Subscriber subscriber);

    List<Subscriber> getMsisdn();

    Page<Subscriber> getPagination(int pageNo, int pageSize, String sortField, String sortDirection);

    List<Subscriber> getByKeyword(String Keyword);

//   Subscriber findSubscriberById(Integer id);

   Subscriber updateSubscriberPage(Integer id);
   Subscriber updateSubscriber(Integer id,Subscriber subscriber);

   long countSubscribers();

}
