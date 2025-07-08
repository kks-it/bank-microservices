package com.bank.accounts.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("spring.jpa")
@Getter
@Setter
@Component
public class JpaConfig {

    private boolean showSql;
    private String databasePlatform;
    private Hibernate hibernate;

    public static class Hibernate {
        private String ddlAuto;

        public String getDdlAuto() {
            return ddlAuto;
        }

        public void setDdlAuto(String ddlAuto) {
            this.ddlAuto = ddlAuto;
        }
    }
}


