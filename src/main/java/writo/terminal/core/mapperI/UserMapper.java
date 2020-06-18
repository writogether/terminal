package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.*;
import writo.terminal.data.User;
import writo.terminal.view.RegisterView;

@Mapper
public interface UserMapper {

    @Select("select * from writo.user where id=#{id} ")
    User getUserById(@Param("id") long id);

    @Select("select * from writo.user where username=#{username} ")
    User getUserByName(@Param("username") String username);

    @Select("select * from writo.user where phone_number=#{phoneNumber} ")
    User getUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Insert("insert into writo.user (username,password,phone_number) values (#{userView.username}, md5(#{userView.password} ), #{userView.phoneNumber} )")
    void register(@Param("userView") RegisterView userView);

    @Update("update writo.user set description=#{description},username=#{username},email=#{email},phone_number=#{phone_number} where id=#{id}")
    void updateById(@Param("id") long id, String description, String username, String email, String phone_number);

}
