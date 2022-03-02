package com.group5.hawadeeleasemanagementsystem;

import com.group5.hawadeeleasemanagementsystem.entity.UserEntity;
import com.group5.hawadeeleasemanagementsystem.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HawadeeLeaseManagementSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper employeeMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserEntity> employeeEntityList = employeeMapper.selectList(null);
        employeeEntityList.forEach(System.out::println);
    }


}
