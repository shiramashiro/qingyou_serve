package com.wizardry.qingyou_serve.mapper;

import com.wizardry.qingyou_serve.entity.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {

    Test getTest(int id);

}
