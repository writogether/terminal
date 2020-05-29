package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.*;
import writo.terminal.data.Collect;
import writo.terminal.data.Comment;
import writo.terminal.data.Story;
import writo.terminal.data.StoryContent;
import writo.terminal.type.TagType;
import writo.terminal.view.CommentView;
import writo.terminal.view.StoryView;

import java.util.List;

@Mapper
public interface StoryMapper {


    @Update(value = "insert into writo.story_content (content) values(#{content})")
    void upload_story_content(@Param("content") String content);

    @Update(value = "insert into writo.story (author_id,title,father_id,tag) values " +
            "(#{storyView.authorId} ,#{storyView.title} ,#{storyView.fatherId} ,#{storyView.tag} ) ")
    void uploadStory(@Param("storyView") StoryView storyView);

    @Update(value = "update writo.story_content set content=#{content} where id=#{id}")
    void deleteStoryContent(@Param("id") long id, @Param("content") String content);

    @Update(value = "update writo.story set valid=#{valid}where id =#{id}")
    void deleteStory(@Param("id")long id,@Param("valid")boolean valid);


    @Select(value = "select * from writo.story where id=#{id} ")
    Story getStoryById(@Param("id") Integer id);

    @Select(value = "select content from writo.story_content where id=#{id}")
    StoryContent getStoryContentById(@Param("id") long id);

    @Select(value = "select * from writo.story where tag=#{tagType.name} ")
    List<Story> getStoryByType(@Param("tagType") TagType tagType);

    @Select(value = "select * from writo.collect where user_id=#{id}")
    List<Integer> getStoryByCollector(@Param("id") Integer id);

    @Select(value = "select * from writo.story where valid is true")
    List<Story> getAllStory();

    @Insert(value = "insert into writo.comment (commenter_id,story_id,content)values(#{comment.commenterId},#{comment.storyId},#{comment.content})")
    void commentStory(@Param("comment") Comment comment);


}
