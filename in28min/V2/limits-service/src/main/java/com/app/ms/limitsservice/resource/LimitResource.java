package com.app.ms.limitsservice.resource;

import com.app.ms.limitsservice.bean.Limits;
import com.app.ms.limitsservice.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitResource {

    @Autowired
    private Configuration configuration;

    @GetMapping("limits")
    public Limits retriveLimits(){
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
