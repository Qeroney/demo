package com.example.demo.feign;

import com.example.demo.feign.dto.CreateProductHistoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "logging-service")
public interface LoggingServiceFeign {

    @PostMapping("history/product/create")
    void createHistory(CreateProductHistoryDto dto);
}
