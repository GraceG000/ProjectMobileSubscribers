package com.gracegh.ProjectMobileSubscribers.Entity;

public enum ServiceType {

    MobilePrepaid("Mobile Prepaid"), MobilePostpaid("Mobile Postpaid");

  private final String serviceType;

  ServiceType(String serviceType){
      this.serviceType = serviceType;
  }
}


