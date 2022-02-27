package com.wizardry.qingyou_serve.service;

import com.wizardry.qingyou_serve.entity.Test;
import com.wizardry.qingyou_serve.mapper.TestMapper;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public TestMapper mapper;

    public TestService(TestMapper mapper) {
        this.mapper = mapper;
    }

    public Test getTest(int id) {
        return mapper.getTest(id);
    }

}
