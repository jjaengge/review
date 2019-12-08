package com.trip.review.repo;

import com.trip.review.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class userRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void test() throws Exception {
        List<User> userList = userRepo.findAll();

        if (userList != null) {
            for (User user : userList) {
                System.out.println(user.getUserId());
            }
        }
    }
}


