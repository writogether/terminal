package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.*;
import writo.terminal.data.Tree;

@Mapper
public interface TreeMapper {

    @Insert("insert into writo.tree (tree) values (#{tree} )")
    @Options(useGeneratedKeys = true, keyProperty = "tree.id", keyColumn = "id")
    void insert(Tree tree);

    @Update("update writo.tree set tree=#{tree} where id=#{id} ")
    void update(@Param("id") long id, @Param("tree") String tree);

    @Select("select * from writo.tree where id=#{id}")
    String select(@Param("id") long id);

}
