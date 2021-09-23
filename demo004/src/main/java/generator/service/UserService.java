package generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import generator.domain.User;
import generator.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 */
public interface UserService extends IService<User> {

    int createUserWrong1(String name);


}
