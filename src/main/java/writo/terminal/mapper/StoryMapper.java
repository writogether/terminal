package writo.terminal.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import writo.terminal.data.StoryContent;

@Mapper
public interface StoryMapper {

    @Update(value = "insert into writo.story_content (content) values(#{content})")
    void upload_story_content(@Param("content")String content);

    @Update(value = "insert into writo.story (author,title,father_id) values(#{author_id},#{title},#{father_id}) ")
    void uploadStory(@Param("father_id")long father_id,@Param("author_id") long author_id,@Param("title")String title);

    @Select(value = "select content from writo.story_content where id=#{id}")
    StoryContent getStoryContentById(@Param("id")long id);

    @Update(value = "update writo.story_content set content=#{content} where id=#{id}")
    void delete_story_content(@Param("id")long id,@Param("content")String content);
}
