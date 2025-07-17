package com.apiVerse.UserCase;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ulmas")

public interface Ulkaas {


    @PostMapping("/kafka/send")
    String sendMEssage(@RequestBody String text);

}
