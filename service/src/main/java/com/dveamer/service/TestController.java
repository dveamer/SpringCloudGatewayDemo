package com.dveamer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final String profile;

    TestController(@Value("${spring.profiles.active}") String profile) {
        this.profile = profile;
    }

    @GetMapping("/")
    public ResponseJson home() {
        return new ResponseJson("Hello world from " + profile);
    }

    @GetMapping("/exception")
    public ResponseJson exception() throws Exception {

        if("service03".equals(profile)) {
            int i = 1/0;
        }

        if("service02".equals(profile)) {
            Thread.sleep(10000);
        }

        return new ResponseJson("Success from " + profile);
    }

    private class ResponseJson {

        private final String msg;


        private ResponseJson(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }


}
