package sunzk.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sunzk.entity.TestUser;

/**
 * Created by 82681 on 2017/12/28.
 */

public interface UserMapper {
    @Select("select * from test_user where username = #{username}")
    TestUser getTestUser(@Param("username") String username);
}
