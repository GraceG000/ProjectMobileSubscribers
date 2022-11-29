package com.gracegh.ProjectMobileSubscribers.Entity;

public enum ServiceType {

    MobilePrepaid("MobilePrepaid"), MobilePostpaid("MobilePostpaid");

  private final String serviceType;

  ServiceType(String serviceType){
      this.serviceType = serviceType;
  }
}


