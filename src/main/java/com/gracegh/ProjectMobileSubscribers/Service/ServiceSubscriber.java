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


    private final SubscriberRepository subscriberRepository;

    @Autowired
    public ServiceSubscriber(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }


    //adding a new subscriber to the database...
    @Override
    public Subscriber addNewSubscriber(Subscriber subscriber) {
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
    public void updateServiceType(Long id) {

        Subscriber subscriber = subscriberRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Subscriber with id " + id + " does not exist."));

        ServiceType serviceType = subscriber.getServiceType();
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