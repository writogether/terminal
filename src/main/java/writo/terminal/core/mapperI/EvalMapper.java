package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import writo.terminal.data.Eval;

@Mapper
public interface EvalMapper {
    @Insert(value = "insert into writo.eval (user_id,story_id,type) values(#{eval.userId},#{eval.storyId},#{type})")
    void evalStory(@Param("eval") Eval eval);
}
