package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.*;
import writo.terminal.data.Eval;

@Mapper
public interface EvalMapper {
    @Insert("insert into writo.eval (user_id,story_id,type) values(#{eval.userId},#{eval.storyId},#{eval.type})")
    void evalStory(@Param("eval") Eval eval);

    @Select("select * from writo.eval where story_id=#{storyId} and user_id=#{userId}")
    Eval getEval(@Param("storyId") Long storyId,@Param("userId") Long userId);

}
