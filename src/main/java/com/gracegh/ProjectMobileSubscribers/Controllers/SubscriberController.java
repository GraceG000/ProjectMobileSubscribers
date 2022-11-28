package com.gracegh.ProjectMobileSubscribers.Controllers;

import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;
import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import com.gracegh.ProjectMobileSubscribers.Service.ServiceSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping
    public class SubscriberController {

        private final ServiceSubscriber serviceSubscriber;

        @Autowired
        public SubscriberController( ServiceSubscriber serviceSubscriber) {
            this.serviceSubscriber = serviceSubscriber;
        }

        @GetMapping(path = "/")
        public String welcome(Model model){
            model.addAttribute("pageName", "Index");
            return "index";
        }

        //returning all mobile numbers from the database...
        @PostMapping("/all_mobile_numbers")
        public List<Subscriber> getMsisdn(){
            return serviceSubscriber.getMsisdn();
        }

        //returning all mobile numbers that match the search criteria...
        @GetMapping(path = "/search")
        public List<Subscriber> searchMobileNumber(@RequestParam(required = false) String keyword){
            return serviceSubscriber.searchNumbers(keyword);
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
