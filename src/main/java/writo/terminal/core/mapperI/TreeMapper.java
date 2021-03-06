package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.*;
import writo.terminal.data.Tree;

@Mapper
public interface TreeMapper {

    @Insert("insert into writo.tree (s_exp) values (#{tree.sExp}  )")
    @Options(useGeneratedKeys = true, keyProperty = "tree.id", keyColumn = "id")
    void insert(@Param("tree") Tree tree);

    @Update("update writo.tree set s_exp=#{tree.sExp} where id=#{tree.id} ")
    void update(@Param("tree") Tree tree);

    @Select("select * from writo.tree where id=#{id}")
    Tree select(@Param("id") long id);

}
