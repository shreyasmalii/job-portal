package com.jobPortal.jobPortal.Controller;
import com.jobPortal.jobPortal.Model.User;
import com.jobPortal.jobPortal.Model.UserProfile;
import com.jobPortal.jobPortal.Services.Interface.JobService;
import com.jobPortal.jobPortal.Services.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JobService jobService;

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("role","user");
        return "login";
    }
    @PostMapping("/login")
    public String authenticate(@ModelAttribute User user){
        if(!userService.isValid(user)){
            return "redirect:/user/login";
        }
        return "redirect:/user/jobList/"+user.getEmail();
    }
    @GetMapping("/sign-up")
    public String signUp(Model model){
        model.addAttribute("role","user");
        return "signUp";
    }
    @PostMapping("/sign-up")
    public String addUser(@ModelAttribute User user){
        if(userService.isValid(user)){
            return "signUp";
        }
        userService.addUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("/jobList/{email}")
    public String jobList(Model model, @PathVariable("email") String email) {
        model.addAttribute("jobs", jobService.getAllJobs());
        model.addAttribute("email", email);
        return "jobListUser";
    }
    @GetMapping("/updateProfile/{email}")
    public String updateProfile(@PathVariable("email") String email,Model model) {
        UserProfile userProfile = userService.getUserProfile(email);
        if(userProfile != null) {
            model.addAttribute("userProfile", userProfile);
        }
        else {
            model.addAttribute("userProfile", new UserProfile());
        }
        model.addAttribute("email", email);
        return "profile";
    }
    @PostMapping("/profile/update/{email}")
    public String update(Model model, @ModelAttribute UserProfile userProfile, @PathVariable String email){
        userService.addProfile(email, userProfile);
        return "redirect:/user/jobList/"+email;
    }

    @GetMapping("/apply/{email}/{id}")
    public String apply(@PathVariable String email, @PathVariable long id){
        userService.apply(email,id);
        return "redirect:/user/success";
    }
    @GetMapping("/success")
    @ResponseBody
    public String success(){
        return "Successfully applied to the job";
    }

}

