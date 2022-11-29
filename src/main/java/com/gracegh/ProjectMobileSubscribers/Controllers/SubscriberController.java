package com.gracegh.ProjectMobileSubscribers.Controllers;

import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;
import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import com.gracegh.ProjectMobileSubscribers.Service.ServiceSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @Controller
    @RequestMapping
    @Slf4j
    public class SubscriberController {

        private final ServiceSubscriber serviceSubscriber;

        @Autowired
        public SubscriberController( ServiceSubscriber serviceSubscriber) {
            this.serviceSubscriber = serviceSubscriber;
        }

        /*@GetMapping(path = "/")
        public String welcome(Model model){
            model.addAttribute("pageName", "Index");
            return "index";
        }*/

        //returning all mobile numbers from the database...
        @GetMapping("/AllNumbers")
        public String getMsisdn(Model model){
            List<Subscriber> subscribers = serviceSubscriber.getMsisdn();
            log.info("Inside get AllNumbers ==> {}", serviceSubscriber.getMsisdn());
            model.addAttribute("pageName", subscribers);
            return "index";
        }

        @GetMapping(path="/search")
      public String getSubscriber(Subscriber subscriber, Model model, String keyword){
            if(keyword!= null) {
                List<Subscriber> subscribers = serviceSubscriber.searchNumbers(keyword);
                model.addAttribute("list", subscribers);
            }else{
                List<Subscriber> subscribers =serviceSubscriber.getMsisdn();
                model.addAttribute("list", subscribers);
                return "index";
            }
            return "index";
        }

        //adding a new subscriber...
        @PostMapping//this is to make this function work...
        public void addNewSubscriber(@RequestBody Subscriber subscriber){
            //@RequestBody allows us to take the subscriber details the client provides...
            //invoking the service class...
            serviceSubscriber.addNewSubscriber(subscriber);
        }

        //changing a mobile number plan from prepaid to postpaid...
        @PutMapping(path="/change-service-type")
        public void updateServiceType(@PathVariable("change-service-type") long id,
                @RequestParam(required = false)ServiceType serviceType){
            serviceSubscriber.updateServiceType(id, serviceType);
        }
}
