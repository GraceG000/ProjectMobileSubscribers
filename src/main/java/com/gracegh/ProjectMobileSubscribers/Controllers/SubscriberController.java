package com.gracegh.ProjectMobileSubscribers.Controllers;

import com.gracegh.ProjectMobileSubscribers.Entity.ServiceType;
import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import com.gracegh.ProjectMobileSubscribers.Service.ServiceSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

//        @GetMapping(path="/search")
//      public String getSubscriber(Subscriber subscriber, Model model, String keyword){
//            if(keyword!= null) {
//                List<Subscriber> subscribers = serviceSubscriber.searchNumbers(keyword);
//                model.addAttribute("list", subscribers);
//            }else{
//                List<Subscriber> subscribers =serviceSubscriber.getMsisdn();
//                model.addAttribute("list", subscribers);
//                return "index";
//            }
//            return "index";
//        }
        @RequestMapping(path={"/search"})

        public String searchData(@ModelAttribute(value = "keyword") String subscriber, Model model, @Param(value="keyword") String keyword) {
       /*   if(keyword!=null) {
                List<Subscriber> list = serviceSubscriber.getByKeyword(keyword);
                model.addAttribute("list", list);
            }else {
                List<Subscriber> list = serviceSubscriber.getMsisdn();
                model.addAttribute("list", list);
           }
            return "index";*/
          //  return serviceSubscriber.getByKeyword(keyword);
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

        //changing a mobile number plan from prepaid to postpaid...
        @PutMapping(path="/updateValue/{id}")
        public ModelAndView updateSubscriber(@PathVariable(name ="id") Integer id){

            ModelAndView edit = new ModelAndView("addNew");
            Subscriber subscriber = (Subscriber) serviceSubscriber.findSubscriberById(id);
            edit.addObject("subscriber", subscriber);
            return edit;
        }

        //deleting a mobile subscriber...
        @GetMapping (path = "/delete/{id}")
        public String deleteSubscriber(@PathVariable(name="id") Integer id){
          serviceSubscriber.deleteSubscriber(id);
          return "redirect:/AllNumbers";

        }

    }
