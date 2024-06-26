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
import java.util.Objects;


@Service
public class ServiceSubscriber implements SubscriberService {

   /* lines 22 to 27 are a typical example of performing dependency injection....*/
    private final SubscriberRepository subscriberRepository;

    @Autowired
    public ServiceSubscriber(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }


    /* adding a new subscriber to the database...*/
    @Override
    public Subscriber addNewSubscriber(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }
    @Override
    public Subscriber addNewSubscriber2(Subscriber subscriber){
        return subscriberRepository.save(subscriber);
    }

    /* returning all mobile numbers from the database...*/
    @Override
    public List<Subscriber> getMsisdn() {
        return subscriberRepository.findAll();
    }

    /* Backend pagination implementation...*/
    @Override
    public Page<Subscriber> getPagination(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo -1 , pageSize, sort);
        return this.subscriberRepository.findAll(pageable);
    }

    @Override
    public Page<Subscriber> searchSort(int pageNo, int pageSize, String sortField, String sortDirection, String keyword){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo -1 , pageSize, sort);
        return subscriberRepository.searchSubscriber(keyword.trim().toLowerCase(), pageable);
    }

    /* search by keyword implementation...It is not being used...*/
    @Override
    public List<Subscriber> Search(String keyword) {
        return subscriberRepository.findAll(keyword);
    }

    /* editing the subscriber information...*/
    @Transactional
    public Subscriber updateSubscriberPage(Integer id) {

        Subscriber subscriber = (Subscriber) subscriberRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Subscriber with id " + id + " does not exist."));

        return subscriber;
    }

    /* implementation for updating subscriber information...*/
    @Transactional
    public Subscriber updateSubscriber(Integer id, Subscriber subscriber){
        Subscriber existingSubscriber = (Subscriber) subscriberRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Subscriber with id " + id + " does not exist."));

        if(existingSubscriber.getMsisdn() != null && existingSubscriber.getMsisdn().length() > 0 && !Objects.equals(subscriber.getMsisdn(), existingSubscriber.getMsisdn())){
            existingSubscriber.setMsisdn(subscriber.getMsisdn());
        }
        if(existingSubscriber.getServiceType() != null && !Objects.equals(subscriber.getServiceType(), existingSubscriber.getServiceType())){
            existingSubscriber.setServiceType(subscriber.getServiceType());
        }

        if(existingSubscriber.getServiceStartDate() != null && !Objects.equals(existingSubscriber.getServiceStartDate(), subscriber.getServiceStartDate())){
            existingSubscriber.setServiceStartDate(subscriber.getServiceStartDate());
        }

        return subscriberRepository.save(existingSubscriber);
    }

    /* deleting a subscriber from the database...*/
    @Transactional
    public List<Subscriber> deleteSubscriber(Integer id){
        boolean exists = subscriberRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Subscriber with id " + id + " does not exist." );
       }
       return subscriberRepository.deleteSubscriberById(id);
    }

   /* finding the subscriber by Msisdn...Although, this function is not being used...*/
    public List<Subscriber> getByKeyword(String keyword){
      return subscriberRepository.findSubscriberByMsisdn(keyword);
    }

    /* Counting some attributes on the table for statistical purposes...*/
    public long countSubscribersStats1(){
        return subscriberRepository.countSubscriberByServiceType(ServiceType.MobilePrepaid);
    }
    public long countSubscribersStats2(){
        return subscriberRepository.countSubscriberByServiceTypeIs(ServiceType.MobilePostpaid);
    }
    public long countSubscribers(){
        return subscriberRepository.count();
    }


}