package com.bank.accounts.controller;


import com.bank.accounts.dto.ApplicationDetailsDto;
import com.bank.accounts.service.impl.AccountsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private Environment environment;

    @Autowired
    private JpaConfig dbConfig;

    @Autowired
    private ApplicationDetailsDto applicationDetailsDto;

    private final AccountsServiceImpl accountsService;

    public HelloWorldController(AccountsServiceImpl accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping("/hello")
    public String getMessage(){
        return "Hello world, db url is : " + dbUrl ;
    }

    @GetMapping("env")
    public String getEnvironmentProperties(){
        return " username is: " + environment.getProperty("spring.datasource.username");
    }

    @GetMapping("db")
    public String getDbDetails(){
        return dbConfig.getDatabasePlatform() + " : - : " + dbConfig.isShowSql() + dbConfig.getHibernate().getDdlAuto();
    }


    @GetMapping("/app-details")
    public String getApplicationDetails(){
        return applicationDetailsDto.message() + " \n " + applicationDetailsDto.contact() + " \n " + applicationDetailsDto.onCallSupport();
    }
}
