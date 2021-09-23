package generator.mapper;

import generator.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity generator.domain.User
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {


}




