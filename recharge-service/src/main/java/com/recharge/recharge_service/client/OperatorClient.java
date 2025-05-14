package com.recharge.recharge_service.client;

import com.recharge.recharge_service.dto.OperatorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "operator-service")
public interface OperatorClient {

    @GetMapping("/api/operators/{id}")
    OperatorDto getOperatorById(@PathVariable("id") String id);
}
