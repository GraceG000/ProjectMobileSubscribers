package com.gracegh.ProjectMobileSubscribers.Service;

import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;
import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import com.gracegh.ProjectMobileSubscribers.Repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ServiceSubscriber implements SubscriberService {

   /* private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ANY = ExampleMatcher
            .matchingAny()
            .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact())
            .withMatcher("msisdn", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("customerIdUser", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("serviceType", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("serviceStartDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("birthDate", ExampleMatcher.GenericPropertyMatchers.exact())
            .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnorePaths("employeeId", "gender", "hireDate", "salaries", "titles");
*/

    private final SubscriberRepository subscriberRepository;

    @Autowired
    public ServiceSubscriber(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }


    //adding a new subscriber to the database...
    @Override
    public void addNewSubscriber(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }



    //returning all mobile numbers from the database...
    @Override
    public List<Subscriber> getMsisdn() {
        return subscriberRepository.findAll();
    }

    //return all mobile numbers that match the search criteria...
//    public List<Subscriber> searchNumbers(String keyword) {
//        if (keyword.isBlank()) {
//            return subscriberRepository.findAll();
//        } else {
//            return subscriberRepository.searchSubscribers('%' + keyword + '%');
//        }
//    }

    public List<Subscriber> searchNumbers(String keyword){
        return subscriberRepository.searchSubscribers(keyword);
    }

    @Transactional
    public void updateServiceType(Long id, ServiceType serviceType) {

        Subscriber subscriber = subscriberRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Subscriber with id " + id + " does not exist."));

        serviceType = subscriber.getServiceType();
        switch (serviceType) {
            case MobilePrepaid:
                subscriber.setServiceType(ServiceType.MobilePostpaid);
                break;
            case MobilePostpaid:
                subscriber.setServiceType(ServiceType.MobilePrepaid);
                break;
            default:
                System.out.println(" ");
                break;
        }
    }
}