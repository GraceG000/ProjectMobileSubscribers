package com.gracegh.ProjectMobileSubscribers.Service;

import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;
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

    Page<Subscriber> searchSort(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);

    //  List<Subscriber> getByKeyword(Integer keyword1);
    List<Subscriber> Search(String keyword);

 //   List<Subscriber> SearchIdOwner(Integer keyword2);

//   Subscriber findSubscriberById(Integer id);

   Subscriber updateSubscriberPage(Integer id);
   Subscriber updateSubscriber(Integer id,Subscriber subscriber);

 long countSubscribers();

 long countSubscribersStats1();

 long countSubscribersStats2();


}
