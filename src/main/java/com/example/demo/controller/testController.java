package com.example.demo.controller;
import java.util.Date;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class testController {

  private Logger LOG = LoggerFactory.getLogger(getClass());
     @GetMapping("/test")
    public String test(HttpServletRequest request) {

        return "worawit";
    }

    @GetMapping("/test5sec")
    public String test5sec(HttpServletRequest request) throws InterruptedException {
        
        Thread.sleep(5000);
        return "worawit test5sec";
    }

    @GetMapping("/caldate")
    public String caldate(HttpServletRequest request) throws InterruptedException {  
        Date testDate = new Date();
        LOG.info("[date]:"+testDate);
        java.sql.Date sqlDate = new java.sql.Date(testDate.getTime());
        String dateAsString = sqlDate.toString(); // จะได้ผลลัพธ์เป็น String
        LOG.info("[sqlDate]:"+dateAsString);
        return "worawit test5sec";
    }

}
