package com.insight.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardApplicationTests {
    // Junit5부터는 ExtendsWith로 사용
    // Junit4는 RunWith로 사용했음.
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Test
    public void contextLoads(){

    }
    @Test
    public void testSqlSession() throws Exception{
        System.out.println(sqlSession.toString());
    }

}
