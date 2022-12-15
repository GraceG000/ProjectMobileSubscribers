package com.gracegh.ProjectMobileSubscribers.Service;

import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import org.springframework.data.domain.Page;
import java.util.List;

public interface SubscriberService {

    /* adding a new subscriber to the database...*/
    Subscriber addNewSubscriber2(Subscriber subscriber);
    Subscriber addNewSubscriber(Subscriber subscriber);

    /*getting the list of subscribers in the database...*/
    List<Subscriber> getMsisdn();

    /*performing backend pagination without the search feature...*/
    Page<Subscriber> getPagination(int pageNo, int pageSize, String sortField, String sortDirection);

    /*integrating the search feature with the backend pagination...*/
    Page<Subscriber> searchSort(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);

    List<Subscriber> Search(String keyword);

   /*updating subscriber information...*/
    Subscriber updateSubscriberPage(Integer id);
    Subscriber updateSubscriber(Integer id,Subscriber subscriber);

   /*getting the database statistics...*/
    long countSubscribers();

    long countSubscribersStats1();

    long countSubscribersStats2();


}
