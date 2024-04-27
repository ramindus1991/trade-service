package com.rum.orderservice.feign;

import com.rum.orderservice.dto.UpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "portfolio-service", url = "${portfolio.uri}")
public interface PortfolioServiceClient {

    @PostMapping("/portfolio/{id}")
    public void sendUpdate(@PathVariable("id") String id, @RequestBody UpdateRequest updateRequest);

}