package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.*;
import writo.terminal.data.Collect;

import java.util.List;

@Mapper
public interface CollectMapper {
    @Insert("insert into writo.collect (user_id, story_id) values (#{collect.userId} ,#{collect.storyId} )")
    void collectStory(@Param("collect") Collect collect);

    @Select("select * from writo.collect where user_id=#{user_id} and story_id=#{story_id}")
    Collect select(@Param("user_id") long user_id, @Param("story_id") long story_id);

    @Delete("delete  from writo.collect where user_id=#{collect.userId} and story_id=#{collect.storyId}")
    void noCollect(@Param("collect") Collect collect);
}
