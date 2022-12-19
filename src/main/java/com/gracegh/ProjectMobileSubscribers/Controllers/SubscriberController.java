package com.gracegh.ProjectMobileSubscribers.Controllers;

import com.gracegh.ProjectMobileSubscribers.Entity.Subscriber;
import com.gracegh.ProjectMobileSubscribers.Service.ServiceSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

       /* returning all mobile numbers from the database...*/
        @GetMapping({"/AllNumbers"})
        public String getMsisdn(Model model){
            return findPaginated(1, "serviceType", "asc","",10, model);
        }

       /*  for pagination...
        @GetMapping("/page/{pageNo}")
        public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                    @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                    @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
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
        }*/

      /* for backend pagination with search implementation...*/
        @GetMapping("/page/{pageNo}")
        public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                    @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                    @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                                    @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                    @RequestParam(value = "pageSize" , defaultValue = "10", required = false) int pageSize,
                                    Model model) {
           //int pageSize = 10;

            Page <Subscriber> page = null;

            if(keyword.trim() == "" || keyword.isEmpty()) {
                page = serviceSubscriber.getPagination(pageNo, pageSize, sortField, sortDir);
            }
            else{
               page = serviceSubscriber.searchSort(pageNo,pageSize,sortField,sortDir,keyword);
            }
             List <Subscriber > subscribers = page.getContent();
             model.addAttribute("currentPage", pageNo);
             model.addAttribute("totalPages", page.getTotalPages());
             model.addAttribute("totalItems", page.getTotalElements());
             model.addAttribute("keyword", keyword);
             model.addAttribute("pageSize", pageSize);
             model.addAttribute("sortField", sortField);
             model.addAttribute("sortDir", sortDir);
             model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

             model.addAttribute("subs", subscribers);

             return "index";
        }

     /* for the frontend pagination interface...*/
        @GetMapping({"/AllNumbersDT"})
        public String getMsisdnDT(Model model){
            List<Subscriber> subscribers = serviceSubscriber.getMsisdn();
            log.info("Inside get AllNumbers ==> {}", serviceSubscriber.getMsisdn());
            model.addAttribute("pageName", subscribers);
            return "index-dt";
        }
     /* for the search functionality...Although, it is not being used...*/
        @RequestMapping(path={"/search"})

        public String searchData(@ModelAttribute(value = "keyword") String subscriber, Model model, @Param(value="keyword") String keyword) {
            model.addAttribute("subs",serviceSubscriber.Search(keyword));
            return "index";
        }
      /* adding a new subscriber feature which will be used in index.html...*/
        @GetMapping (path="/addon")//this is to make this function work...
        public String addNewSubscriberPage(Model model){
        //@RequestBody allows us to take the subscriber details the client provides...
            Subscriber subscriber= new Subscriber();
            model.addAttribute("subscriber", subscriber);
            return "addNew";
        }
      /* adding a new subscriber feature which will be used in index-dt.html...*/
        @GetMapping (path="/page/addon2")//this is to make this function work...
        public String addNewSubscriberPageX(Model model){
            //invoking the service class...
            Subscriber subscriber= new Subscriber();
            model.addAttribute("subscriber", subscriber);
            return "AddNew2";
        }

      /* saving a new subscriber feature, which will be used in index-dt.html...*/
        @PostMapping(value = "/save-subscriber")//this is to make this function work...
        public String addNewSubscriber(@Valid @ModelAttribute Subscriber subscriber, Model model){
            //invoking the service class...
            Subscriber newSubscriber = serviceSubscriber.addNewSubscriber(subscriber);
            model.addAttribute("pageName", newSubscriber);
            return "redirect:/AllNumbersDT";
        }
//todo

     /* saving a new subscriber feature, which will be implemented in index.html...*/
        @PostMapping(value = "/save-subscribers")//this is to make this function work...
        public String addNewSubscriber2(@Valid @ModelAttribute Subscriber subscriber, Errors errors, Model model) {
            if(errors.hasErrors()){
                return "redirect:/AllNumbers";
            }else{
                //invoking the service class...
                Subscriber newSubscriber2 = serviceSubscriber.addNewSubscriber2(subscriber);
                model.addAttribute("pageName2", newSubscriber2);
                return "redirect:/AllNumbers";
        }
        }

     /* updating subscriber information...
        to do this, I used a get and a post mapping...
        the get mapping is used to display the update.html page...*/
        @GetMapping(path="/updatePage/{id}")
        public String updateSubscriberPage(Model model, Subscriber subscriber,@PathVariable(name = "id") Integer id){
            //invoking the service class...
            model.addAttribute("subscriber",   serviceSubscriber.updateSubscriberPage(id));
            return "update";
        }

     /* the post mapping is t implement the update functionality in the backend pagination homepage...*/
        @PostMapping(path="/updateValue/{id}")
        public String updateSubscriber(@Valid @ModelAttribute Subscriber subscriber, Model model,@PathVariable(name="id")Integer id){
            Subscriber changedSubscriber = serviceSubscriber.updateSubscriber(id, subscriber);
            model.addAttribute("updated", changedSubscriber);
            return "redirect:/AllNumbers";
        }
     /* updating subscriber information from the frontend pagination page...*/
        @GetMapping(path="/updatePage2/{id}")
        public String updateSubscriberPageX(Model model, Subscriber subscriber,@PathVariable(name = "id") Integer id){
            //invoking the service class...
            model.addAttribute("subscriber",   serviceSubscriber.updateSubscriberPage(id));
            return "update";
        }

        @PostMapping(path="/updateValue2/{id}")
        public String updateSubscriberX(@Valid @ModelAttribute Subscriber subscriber, Model model,@PathVariable(name="id")Integer id){
            Subscriber changedSubscriber = serviceSubscriber.updateSubscriber(id, subscriber);
            model.addAttribute("updated", changedSubscriber);
            return "redirect:/AllNumbersDT";
        }

     /* deleting a mobile subscriber...*/
        @GetMapping (path = "/delete/{id}")
        public String deleteSubscriber(@PathVariable(name="id") Integer id){
            serviceSubscriber.deleteSubscriber(id);
            return "redirect:/AllNumbers";
        }

     /* displaying the database/table statistics...*/
        @GetMapping(path="/stats")
        public String setStatsPage(Model model){
            long count = serviceSubscriber.countSubscribers();
            long count2 = serviceSubscriber.countSubscribersStats1();
            long count3 = serviceSubscriber.countSubscribersStats2();

            model.addAttribute("countSubscribers", count);
            model.addAttribute("countPrepaid", count2);
            model.addAttribute("countPostpaid", count3);

            return "Statistics";
        }
    }
