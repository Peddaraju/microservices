package com.app.ms.limitsservice.resource;

import com.app.ms.limitsservice.bean.Limits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitResource {

    @GetMapping("limits")
    public Limits retriveLimits(){
        return new Limits(1, 2000);
    }
}
