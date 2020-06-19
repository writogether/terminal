package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import writo.terminal.data.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from writo.comment where story_id=#{story_id}")
    List<Comment> getComment(@Param("story_id") long story_id);

    @Insert("insert into writo.comment (commenter_id,story_id,content)values(#{comment.commenterId},#{comment.storyId},#{comment.content})")
    void commentStory(@Param("comment") Comment comment);

}
