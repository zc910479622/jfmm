package com.jinfei.jfmm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Pop")
public class PopController {

    @RequestMapping("/popBMould")
    public String popBMould(){
        return "/Pop/popBMould";
    }

    @RequestMapping("/popMMain")
    public String popMMain(){
        return "/Pop/popMMain";
    }

    @RequestMapping("/popMMould")
    public String popMMould(){
        return "/Pop/popMMould";
    }

    @RequestMapping("/popTz")
    public String popTZ(){
        return "/Pop/popTz";
    }

}
