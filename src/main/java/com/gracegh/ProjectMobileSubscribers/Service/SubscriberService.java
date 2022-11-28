package com.gracegh.ProjectMobileSubscribers.Service;

import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;

import java.util.List;

public interface SubscriberService {


    List<Subscriber> getMsisdn();

    void addNewSubscriber(Subscriber subscriber);
}
