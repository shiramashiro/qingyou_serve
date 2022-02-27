package com.wizardry.qingyou_serve.controller;

import com.wizardry.qingyou_serve.entity.Test;
import com.wizardry.qingyou_serve.service.TestService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    public TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    @RequestMapping("/get/test/{id}")
    public Test getTest(@PathVariable int id) {
        return service.getTest(id);
    }

}
