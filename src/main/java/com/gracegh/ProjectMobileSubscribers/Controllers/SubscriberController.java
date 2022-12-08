package com.gracegh.ProjectMobileSubscribers.Controllers;

import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import com.gracegh.ProjectMobileSubscribers.Service.ServiceSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
        @GetMapping({"/AllNumbers"})
        public String getMsisdn(Model model){
            return findPaginated(1, "serviceType", "asc", model);
        }
        @GetMapping("/page/{pageNo}")
        public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                    @RequestParam("sortField") String sortField,
                                    @RequestParam("sortDir") String sortDir,
                                    Model model) {
            int pageSize = 10;

            Page < Subscriber > page = serviceSubscriber.getPagination(pageNo, pageSize, sortField, sortDir);
            List <Subscriber > subscribers = page.getContent();

            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());

            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

            model.addAttribute("subs", subscribers);
            return "index";
        }

        @GetMapping({"/AllNumbersDT"})
        public String getMsisdnDT(Model model){
            List<Subscriber> subscribers = serviceSubscriber.getMsisdn();
            log.info("Inside get AllNumbers ==> {}", serviceSubscriber.getMsisdn());
            model.addAttribute("pageName", subscribers);
            return "index-dt";
        }

        @RequestMapping(path={"/search"})

        public String searchData(@ModelAttribute(value = "keyword") String subscriber, Model model, @Param(value="keyword") String keyword) {

            model.addAttribute("subs",serviceSubscriber.getByKeyword(keyword));
            return "index";
        }

        //adding a new subscriber...
        @GetMapping (path="/addon")//this is to make this function work...
        public String addNewSubscriberPage(Model model){
            //@RequestBody allows us to take the subscriber details the client provides...
            //invoking the service class...
          //  serviceSubscriber.addNewSubscriber(subscriber);
            Subscriber subscriber= new Subscriber();
            model.addAttribute("subscriber", subscriber);
            return "addNew";
        }

        @GetMapping (path="/page/addon2")//this is to make this function work...
        public String addNewSubscriberPageX(Model model){
            //@RequestBody allows us to take the subscriber details the client provides...
            //invoking the service class...
          //  serviceSubscriber.addNewSubscriber(subscriber);
            Subscriber subscriber= new Subscriber();
            model.addAttribute("subscriber", subscriber);
            return "AddNew2";
        }

        @PostMapping(value = "/save-subscriber")//this is to make this function work...
        public String addNewSubscriber(@ModelAttribute Subscriber subscriber, Model model){
            //@RequestBody allows us to take the subscriber details the client provides...
            //invoking the service class...
            Subscriber newSubscriber = serviceSubscriber.addNewSubscriber(subscriber);
            model.addAttribute("pageName", newSubscriber);
            return "redirect:/AllNumbersDT";
        }

        @PostMapping(value = "/save-subscribers")//this is to make this function work...
        public String addNewSubscriber2(@ModelAttribute Subscriber subscriber, Model model) {
            //@RequestBody allows us to take the subscriber details the client provides...
            //invoking the service class...
            Subscriber newSubscriber2 = serviceSubscriber.addNewSubscriber2(subscriber);
            model.addAttribute("pageName2", newSubscriber2);
            return "redirect:/AllNumbers";
        }

        //updating subscriber information...

        @GetMapping(path="/updatePage/{id}")
        public String updateSubscriberPage(Model model, Subscriber subscriber,@PathVariable(name = "id") Integer id){
            //@RequestBody allows us to take the subscriber details the client provides...
            //invoking the service class...
            //  serviceSubscriber.addNewSubscriber(subscriber);

            model.addAttribute("subscriber",   serviceSubscriber.updateSubscriberPage(id));
            return "update";
        }

        @PostMapping(path="/updateValue/{id}")
        public String updateSubscriber(@ModelAttribute Subscriber subscriber, Model model,@PathVariable(name="id")Integer id){
            Subscriber changedSubscriber = serviceSubscriber.updateSubscriber(id, subscriber);
            model.addAttribute("updated", changedSubscriber);
            return "redirect:/AllNumbers";
        }

        @GetMapping(path="/updatePage2/{id}")
        public String updateSubscriberPageX(Model model, Subscriber subscriber,@PathVariable(name = "id") Integer id){
            //@RequestBody allows us to take the subscriber details the client provides...
            //invoking the service class...
            //  serviceSubscriber.addNewSubscriber(subscriber);

            model.addAttribute("subscriber",   serviceSubscriber.updateSubscriberPage(id));
            return "update";
        }

        @PostMapping(path="/updateValue2/{id}")
        public String updateSubscriberX(@ModelAttribute Subscriber subscriber, Model model,@PathVariable(name="id")Integer id){
            Subscriber changedSubscriber = serviceSubscriber.updateSubscriber(id, subscriber);
            model.addAttribute("updated", changedSubscriber);
            return "redirect:/AllNumbersDT";
        }
        //deleting a mobile subscriber...
        @GetMapping (path = "/delete/{id}")
        public String deleteSubscriber(@PathVariable(name="id") Integer id){
          serviceSubscriber.deleteSubscriber(id);
          return "redirect:/AllNumbers";

        }

        @GetMapping(path="/countSubscribers")
        @ResponseBody
        public long countSubscribers(){

            return serviceSubscriber.countSubscribers();
        }

    }
