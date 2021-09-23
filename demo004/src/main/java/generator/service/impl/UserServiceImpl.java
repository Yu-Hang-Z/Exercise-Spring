package generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.User;
import generator.service.UserService;
import generator.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int createUserWrong1(String name) {
        try {
            this.createUserPrivate(new User(name));
        }catch (Exception ex){
            log.error("create user failed because{}",ex.getMessage());
        }
        Map<String, String> map = new HashMap<>();
        map.put("name",name);
        return userMapper.selectList(new QueryWrapper<User>()
                .lambda()
                .eq(User::getName,name)
        ).size();
    }


    public int getUserCount(String name) {
        return userMapper.selectList(new LambdaQueryWrapper<User>()
        .eq(User::getName,name)).size();
    }

    //标记了eTransactional的private方法
    @Transactional
    public void createUserPrivate(User user) {
        userMapper.insert(user);
        if (user.getName().contains("test")) {
            throw new RuntimeException(" invalid username!");
        }
    }
}




