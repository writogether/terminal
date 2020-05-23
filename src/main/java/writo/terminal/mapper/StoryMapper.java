package writo.terminal.mapper;

import org.apache.ibatis.annotations.*;
import writo.terminal.data.Collect;
import writo.terminal.data.Story;
import writo.terminal.data.StoryContent;
import writo.terminal.type.TagType;
import writo.terminal.view.StoryView;

import java.util.List;

@Mapper
public interface StoryMapper {

    @Insert(value = "insert into writo.collect (user_id, story_id) values (#{collect.userId} ,#{collect.storyId} )")
    void collectStory(@Param("collect") Collect collect);

    @Update(value = "insert into writo.story_content (content) values(#{content})")
    void upload_story_content(@Param("content") String content);

    @Update(value = "insert into writo.story (author_id,title,father_id,tag) values " +
            "(#{storyView.authorId} ,#{storyView.title} ,#{storyView.fatherId} ,#{storyView.tag} ) ")
    void uploadStory(@Param("storyView") StoryView storyView);

    @Update(value = "update writo.story_content set content=#{content} where id=#{id}")
    void delete_story_content(@Param("id") long id, @Param("content") String content);

    @Select(value = "select content from writo.story_content where id=#{id}")
    StoryContent getStoryContentById(@Param("id") long id);

    @Select(value = "select * from writo.story where tag=#{tagType.name} ")
    List<Story> getStoryByType(@Param("tagType") TagType tagType);

}
