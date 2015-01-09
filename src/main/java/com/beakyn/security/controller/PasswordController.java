/*
 * Copyright 2013 Stormpath, Inc. and contributors.
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

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.resource.ResourceException;
import com.beakyn.security.model.User;
import com.beakyn.security.model.dao.CustomerDao;
import com.beakyn.security.model.sdk.StormpathService;
import com.beakyn.security.validator.ChangePasswordValidator;
import com.beakyn.security.validator.ResetPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author Elder Crisostomo
 */
@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PasswordController {

    ChangePasswordValidator changePasswordValidator;

    ResetPasswordValidator resetPasswordValidator;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    StormpathService stormpath;

    public PasswordController() {
    }

    @Autowired
    public PasswordController(ChangePasswordValidator changePasswordValidator, ResetPasswordValidator resetPasswordValidator) {
        this.changePasswordValidator = changePasswordValidator;
        this.resetPasswordValidator = resetPasswordValidator;
    }

    @RequestMapping(value = "/password/forgot", method = RequestMethod.GET)
    public String initResetPassword(Model model) {
        model.addAttribute("user", new User());
        return "resetPassword";
    }

    @RequestMapping(value = "/password/forgot", method = RequestMethod.POST)
    public String processResetPassword(User customer, BindingResult result) {

        resetPasswordValidator.validate(customer, result);

        if (result.hasErrors()) {
            return "resetPassword";
        } else {
            try {
                stormpath.getApplication().sendPasswordResetEmail(customer.getEmail());
            } catch (ResourceException re) {
                result.addError(new ObjectError("email", re.getCode() == 404 ? "The provided email for password reset does not exist." : re.getMessage()));
                re.printStackTrace();
                return "resetPassword";
            }

            //form success
            return "resetPasswordMsg";
        }
    }

    @RequestMapping(value = "/password/message", method = RequestMethod.GET)
    public String initResetPasswordMsg(Model model) {
        User cust = new User();
        model.addAttribute("customer", cust);
        return "resetPasswordMsg";
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.GET)
    public String initChangePassword(User cust, Model model) {

        if (!StringUtils.hasText(cust.getSptoken())) {
            //invalid page access - no one should visit this page unless they're resetting their password and they have
            //a valid sptoken:
            return "redirect:/password/forgot";
        }

        model.addAttribute("customer", cust);
        //show the form:
        return "changePassword";
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    public String processChangePassword(@ModelAttribute("customer") User user, BindingResult result, HttpSession session) {

        changePasswordValidator.validate(user, result);

        if (result.hasErrors()) {
            return "changePassword";
        }

        String sptoken = user.getSptoken();

        if (!StringUtils.hasText(sptoken)) {
            //invalid page access - should have an sptoken from the setup form.
            return "redirect:/password/forgot";
        }

        try {
            //New password was specified - verify the reset token and apply the new password:
            Account account = stormpath.getApplication().verifyPasswordResetToken(sptoken);

            //token is valid, set the password and sync to Stormpath:
            account.setPassword(user.getPassword());
            account.save();

            //remove any stale value:
            session.removeAttribute("stormpathAccount");

        } catch (ResourceException re) {
            result.addError(new ObjectError("password", re.getCode() == 404 ? "The provided password reset token is invalid." : re.getMessage()));
            re.printStackTrace();
            return "changePassword";
        }

        //form success
        return "redirect:/login/message?loginMsg=passChanged";
    }
}
