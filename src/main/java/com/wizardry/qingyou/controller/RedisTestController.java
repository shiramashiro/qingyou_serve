package com.wizardry.qingyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, String> template;

    @RequestMapping(value = "redis/get/{key}", method = RequestMethod.GET)
    public String getRedis(@PathVariable String key) {
        return template.opsForValue().get(key);
    }

    @RequestMapping(value = "redis/set/key/{key}/value/{value}", method = RequestMethod.GET)
    public void setRedis(@PathVariable String key, @PathVariable String value) {
        template.opsForValue().set(key, value);
    }

}
