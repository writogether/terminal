package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import writo.terminal.data.Collect;

import java.util.List;

@Mapper
public interface CollectMapper {
    @Insert(value = "insert into writo.collect (user_id, story_id) values (#{collect.userId} ,#{collect.storyId} )")
    void collectStory(@Param("collect") Collect collect);

    @Select(value = "select * from writo.collect where user_id=#{user_id} and story_id=#{story_id}")
    List<Collect> checkIfCollected(@Param("user_id") long user_id, @Param("story_id") long story_id);

}
