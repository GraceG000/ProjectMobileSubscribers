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

//    List<Subscriber> searchSubscribersByCustomerIdOwnerOrCustomerIdUserOrServiceTypeOrServiceStartDateOrMsisdn(String keyword);

    /*@Query("select f from Subscriber f " +
            "where to_char(f.customerIdOwner) like ?1 " +
            "OR to_char(f.customerIdUser) like ?1 " +
            "or f.msisdn like ?1 " +
            "or to_char(f.serviceStartDate) like ?1 " +
            "or to_char(f.serviceType) like ?1")
    List<Subscriber> searchSubscribers(String keyword);*/

    //using a native query...
//    @Query(value = "select f.* from subscribers f " +
//            "where cast(f.customer_id_owner as varchar) like ?1 " +
//            "OR cast(f.customer_id_user as varchar) like ?1 " +
//            "OR cast(f.msisdn as varchar) like ?1 " +
//            "OR cast(f.service_start_date as varchar) like ?1 " +
//            "OR cast(f.service_type as varchar) like ?1", nativeQuery = true)
//    List<Subscriber> searchSubscribers(@Param ("keyword")String keyword);
//

    List<Subscriber> findAll();
  //  List<Subscriber> findSubscriberByKeyword(String keyword);

    

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

