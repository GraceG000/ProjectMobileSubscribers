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

        //returning all mobile numbers from the database...
        @GetMapping({"/AllNumbers", "/"})
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
            return "addNew";
        }

//        @RequestMapping(value = "/save-subscriber", method = RequestMethod.POST, consumes="application/json")//this is to make this function work...
        @PostMapping(value = "/save-subscriber")//this is to make this function work...
        public String addNewSubscriber(@ModelAttribute Subscriber subscriber, Model model){
            //@RequestBody allows us to take the subscriber details the client provides...
            //invoking the service class...
            Subscriber newSubscriber = serviceSubscriber.addNewSubscriber(subscriber);
            model.addAttribute("pageName", newSubscriber);
            return "redirect:/AllNumbers";
        }

        //changing a mobile number plan from prepaid to postpaid...
        @PutMapping(path="/updateValue")
        public String updateServiceType(@PathVariable("id") Integer id, Subscriber subscriber,
                @RequestParam(required = false)ServiceType serviceType, Model model){

            model.addAttribute("subscriber", subscriber);
            serviceSubscriber.updateServiceType(id);
            return "update";
        }

        //deleting a mobile subscriber...
        @GetMapping (path = "/delete/{id}")
        public String deleteSubscriber(@PathVariable("id") Integer id){
           serviceSubscriber.deleteSubscriber(id);
        return "redirect:/AllNumbers";
        }

    }
