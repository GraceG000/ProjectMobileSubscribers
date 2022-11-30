package com.gracegh.ProjectMobileSubscribers.Service;

import com.gracegh.ProjectMobileSubscribers.DTO.SubscriberDTO;
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
    public Subscriber addNewSubscriber(SubscriberDTO subscriber) {
        Subscriber newSubscriber = new Subscriber();
        newSubscriber.setMsisdn(subscriber.getMsisdn());
        newSubscriber.setCustomerIdOwner(subscriber.getCustomerIdOwner());
        newSubscriber.setCustomerIdUser(subscriber.getCustomerIdUser());
        newSubscriber.setServiceType(subscriber.getServiceType());
          return subscriberRepository.save(subscriber);
    }



    //returning all mobile numbers from the database...
    @Override
    public List<Subscriber> getMsisdn() {
        return subscriberRepository.findAll();
    }


    //searching mobile numbers by various criteria...
    public List<Subscriber> searchNumbers(String keyword){
        return subscriberRepository.searchSubscribers(keyword);
    }

    //editing the subscriber information...
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

    //deleting a subscriber from the database...
    public void deleteSubscriber(Long id){
        boolean exists = subscriberRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Subscriber with id " + id + " does not exist." );
        }
       subscriberRepository.deleteById(id);
    }
}