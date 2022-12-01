package com.gracegh.ProjectMobileSubscribers.Repository;

import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

//    List<Subscriber> searchSubscribersByCustomerIdOwnerOrCustomerIdUserOrServiceTypeOrServiceStartDateOrMsisdn(String keyword);

    /*@Query("select f from Subscriber f " +
            "where to_char(f.customerIdOwner) like ?1 " +
            "OR to_char(f.customerIdUser) like ?1 " +
            "or f.msisdn like ?1 " +
            "or to_char(f.serviceStartDate) like ?1 " +
            "or to_char(f.serviceType) like ?1")
    List<Subscriber> searchSubscribers(String keyword);*/

    //using a native query...
    @Query(value = "select f.* from subscribers f " +
            "where cast(f.customer_id_owner as varchar) like ?1 " +
            "OR cast(f.customer_id_user as varchar) like ?1 " +
            "OR cast(f.msisdn as varchar) like ?1 " +
            "OR cast(f.service_start_date as varchar) like ?1 " +
            "OR cast(f.service_type as varchar) like ?1", nativeQuery = true)
    List<Subscriber> searchSubscribers(@Param ("keyword")String keyword);


    List<Subscriber> findAll();
  //  List<Subscriber> findSubscriberByKeyword(String keyword);


    @Override
    void deleteAllById(Iterable<? extends Long> longs);
}

