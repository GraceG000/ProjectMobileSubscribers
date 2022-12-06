package com.gracegh.ProjectMobileSubscribers.Service;

import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;
import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import com.gracegh.ProjectMobileSubscribers.Repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Subscriber addNewSubscriber2(Subscriber subscriber){return subscriberRepository.save(subscriber);}

    //returning all mobile numbers from the database...
    @Override
    public List<Subscriber> getMsisdn() {
        return subscriberRepository.findAll();
    }

    @Override
    public Page<Subscriber> getPagination(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo -1 , pageSize, sort);
        return this.subscriberRepository.findAll(pageable);
    }


    //searching mobile numbers by various criteria...
//    public List<Subscriber> searchNumbers(String keyword){
//        return subscriberRepository.searchSubscribers(keyword);
//    }

    //editing the subscriber information...
    @Transactional
    public void updateServiceType(Integer id) {

        Subscriber subscriber = (Subscriber) subscriberRepository.findById(id).orElseThrow(() -> new IllegalStateException(
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
    public void deleteSubscriber(Integer id){
        boolean exists = subscriberRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Subscriber with id " + id + " does not exist." );
       }
       subscriberRepository.deleteById(id);

    }

    public List<Subscriber> getByKeyword(String keyword){
        return subscriberRepository.findByKeyword(keyword);
    }
}