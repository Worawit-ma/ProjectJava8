package com.example.demo.controller;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import com.example.demo.service.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class testController {

    @Autowired
    TokenGenerator TokenGenerator;

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
        return "fix result";
    }

    @PostMapping("/api/createToken")
    public String getToken(HttpServletRequest request ,@RequestBody String dataInput) {  
        LOG.info("[/api/createToken]");
        String result = TokenGenerator.generateTokenWithText(dataInput);
        return result;
    }

    @PostMapping("/api/decodeToken")
    public String decodeToken(HttpServletRequest request ,@RequestBody String dataInput) {  
        LOG.info("[/api/decodeToken]");
        String result = TokenGenerator.decodeToken(dataInput.trim());
        return result;
    }

}
