package com.bank.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "application")
public record ApplicationDetailsDto(String message, Map<String, String> contact, List<String> onCallSupport ) {

}