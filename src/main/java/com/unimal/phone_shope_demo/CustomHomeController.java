package com.unimal.phone_shope_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
@Controller
public class CustomHomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "phone_shope_demo");
        String project = "phone_shope_project";
        String dateTime = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'GMT' xxx yyyy"));
        model.addAttribute("project", project);
        model.addAttribute("dateTime", dateTime);
        return "index";
    }
}
