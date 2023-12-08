package org.atalibdev.getway.fallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/customerServiceFallBack")
    public String customerServiceFallBack(){
        return "Customer service is down!!";
    }

    @GetMapping("/accountServiceFallBack")
    public String accountServiceFallBack(){
        return "Account service is down!!!";
    }

    @GetMapping("/webServiceFallBack")
    public String webConsumerService(){
        return "Web Consumer service is down!!";
    }
}
