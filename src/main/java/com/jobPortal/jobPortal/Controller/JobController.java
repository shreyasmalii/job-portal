package com.jobPortal.jobPortal.Controller;

import com.jobPortal.jobPortal.Model.Job;
import com.jobPortal.jobPortal.Services.Interface.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping("/add-job/{email}")
    public String addJobForm(Model model, @PathVariable String email) {
        model.addAttribute("email", email);
        model.addAttribute("job", new Job());
        return "addUpdateJob";
    }
    @PostMapping("/add-job/{email}")
    public String addJob(@PathVariable String email, @ModelAttribute Job job) {
        jobService.addJob(job, email);
        return "redirect:/admin/jobList/"+email;
    }
    @GetMapping("/update-job/{email}/{id}")
    public String updateJobForm(Model model, @PathVariable String email, @PathVariable Long id) {
        model.addAttribute("email", email);
        Job job = jobService.getJobById(id);
        model.addAttribute("job", job);
        return "addUpdateJob";
    }
    @GetMapping("/details/{id}/{email}")
    public String viewDetails(Model model, @PathVariable long id, @PathVariable String email){
        model.addAttribute("job",jobService.getJobById(id));
        model.addAttribute("email", email);
        return "viewDetails";
    }

}
