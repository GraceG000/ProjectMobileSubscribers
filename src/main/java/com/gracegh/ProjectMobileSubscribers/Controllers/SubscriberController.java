package com.gracegh.ProjectMobileSubscribers.Controllers;

import com.gracegh.ProjectMobileSubscribers.DTO.SubscriberDTO;
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
        @GetMapping (path="/addon")//this is to make this function work...
        public String addNewSubscriberPage(Model model){
            //@RequestBody allows us to take the subscriber details the client provides...
            //invoking the service class...
          //  serviceSubscriber.addNewSubscriber(subscriber);
            Subscriber subscriber= new Subscriber();
//            Subscriber subscriber= serviceSubscriber.addNewSubscriber(subscribers);
            model.addAttribute("subscriber", subscriber);
            return "addnew";
        }

        @PostMapping (path="/addNewSubscriber")//this is to make this function work...
        public String addNewSubscriber(@ModelAttribute("subscribers") SubscriberDTO subscriber){
            //@RequestBody allows us to take the subscriber details the client provides...
            //invoking the service class...
            serviceSubscriber.addNewSubscriber(subscriber);
            return "redirect:/";
        }
        //changing a mobile number plan from prepaid to postpaid...
        @PutMapping(path="/update")
        public String updateServiceType(@PathVariable("change-service-type") long id, Subscriber subscriber,
                @RequestParam(required = false)ServiceType serviceType, Model model){

            model.addAttribute("subscriber", subscriber);
            serviceSubscriber.updateServiceType(id, serviceType);
            return "edit";
        }

        //deleting a mobile subscriber...
        @GetMapping (path = "{id}")
        public void deleteStudent(@PathVariable("id") Long id){
           serviceSubscriber.deleteSubscriber(id);
        }

    }
