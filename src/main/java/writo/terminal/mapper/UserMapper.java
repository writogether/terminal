package writo.terminal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import writo.terminal.data.User;
import writo.terminal.view.UserView;

@Mapper
public interface UserMapper {

    @Select(value = "select * from writo.user where id=#{id} ")
    User getUserById(@Param("id") long id);

    @Select(value = "select * from writo.user where username=#{username} and password=md5(#{password})")
    User login(@Param("username") String username, @Param("password") String password);

    @Update(value = "insert into writo.user (username,password) values (#{userView.username}, #{userView.password})")
    void register(@Param("userView") UserView userView);

}
