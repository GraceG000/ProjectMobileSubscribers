package com.gracegh.ProjectMobileSubscribers.Repository;

import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;
import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {

    List<Subscriber> findAll();

    boolean existsById(Integer id);

    List<Subscriber> deleteSubscriberById(Integer id);

    Optional<Subscriber> findById(Integer id);

    List<Subscriber> findSubscriberByMsisdn(String keyword);

    Subscriber findSubscriberById(Integer id);

    long countSubscriberByServiceType(ServiceType serviceType);
    long countSubscriberByServiceTypeIs(ServiceType serviceType);

    @Query("select p from Subscriber p where concat(p.serviceType, '', p.msisdn, '', p.serviceStartDate, '', p.customerIdOwner, '', p.customerIdUser) like %?1%")
     List<Subscriber> findAll(String keyword);
    @Query("select p from Subscriber p where lower(concat(p.serviceType, '', p.msisdn, '', p.serviceStartDate, '', p.customerIdOwner, '', p.customerIdUser)) like %?1%")
    Page<Subscriber> searchSubscriber(String keyword, Pageable pageable);
    
}

