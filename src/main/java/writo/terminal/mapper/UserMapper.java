package writo.terminal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import writo.terminal.data.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select(value = "select * from writo.user where true")
    List<User> getUserById(@Param("id") int id);

}
