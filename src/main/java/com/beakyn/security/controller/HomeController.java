/*
 * Copyright 2012 Stormpath, Inc. and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beakyn.security.controller;

import com.beakyn.security.model.User;
import com.beakyn.security.model.dao.CustomerDao;
import com.beakyn.security.model.sdk.StormpathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * @author Elder Crisostomo
 */
@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class HomeController {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    StormpathService stormpath;


    public HomeController() {
    }


    @RequestMapping(method = RequestMethod.POST, value = "/home")
    public String processSubmit(@ModelAttribute("user") User user,
                                BindingResult result,
                                SessionStatus status,
                                HttpSession session) {


        User sessionUser = (User) session.getAttribute("sessionUser");

        if (!result.hasErrors()) {

            User persistCustomer = new User(sessionUser);


          
        }


        status.setComplete();


        //form success
        return "tooter";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String initForm(ModelMap model,
                           @ModelAttribute("user") User user,
                           BindingResult result,
                           HttpSession session) {



        try {

           user = (User) session.getAttribute("sessionUser");

            if (user == null || user.getId() == null) {
                // if they're not in the session, they're probably not logged in
                return "redirect:/login";
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


        return "home";
    }

}