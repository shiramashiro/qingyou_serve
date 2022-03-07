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

    /**
     * 查询 key 所对应的 value
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "redis/select/key/{key}", method = RequestMethod.GET)
    public String getRedis(@PathVariable String key) {
        return template.opsForValue().get(key);
    }

    /**
     * 插入 key
     *
     * @param key
     * @param value
     */
    @RequestMapping(value = "redis/insert/key/{key}/value/{value}", method = RequestMethod.GET)
    public void setRedis(@PathVariable String key, @PathVariable String value) {
        template.opsForValue().set(key, value);
    }

    /**
     * 删除 key 所对应的 value
     *
     * @param key
     */
    @RequestMapping(value = "redis/delete/key/{key}", method = RequestMethod.GET)
    public void removeRedis(@PathVariable String key) {
        template.opsForValue().getAndDelete(key);
    }

    /**
     * 修改 key 所对应的 value
     *
     * @param key
     * @param value
     */
    @RequestMapping(value = "redis/update/key/{key}/value/{value}", method = RequestMethod.GET)
    public void updateRedis(@PathVariable String key, @PathVariable String value) {
        template.opsForValue().set(key, value);
    }

}
