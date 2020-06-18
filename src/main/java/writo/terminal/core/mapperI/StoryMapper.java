package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import writo.terminal.data.Collect;
import writo.terminal.data.Comment;
import writo.terminal.data.Story;
import writo.terminal.data.StoryContent;
import writo.terminal.type.TagType;
import writo.terminal.view.CommentView;
import writo.terminal.view.StoryView;

import java.util.List;

@Mapper
@Component
public interface StoryMapper {


    @Update(value = "insert into writo.story_content (content) values(#{content})")
    void upload_story_content(@Param("content") String content);

    @Update(value = "insert into writo.story (author_id,title,father_id,tag,depth) values " +
            "(#{storyView.authorId} ,#{storyView.title} ,#{storyView.fatherId} ,#{storyView.tag},#{father_depth}+1 ) ")
    void uploadStory(@Param("storyView") StoryView storyView,@Param("father_depth")int f_depth);

    @Update(value = "update writo.story_content set content=#{content} where id=#{id}")
    void deleteStoryContent(@Param("id") long id, @Param("content") String content);

    @Update(value = "update writo.story set valid=#{valid}where id =#{id}")
    void deleteStory(@Param("id")long id,@Param("valid")boolean valid);


    @Select(value = "select * from writo.story where id=#{id} ")
    Story getStoryById(@Param("id") Integer id);

    @Select(value = "select * from writo.story_content where id=#{id}")
    StoryContent getStoryContentById(@Param("id") long id);

    @Select(value = "select * from writo.story where author_id=#{author_id}")
    List<Story> getStoryByAuthor(@Param("author_id")long author_id);

    @Select(value = "select * from writo.story where tag=#{tagType} ")
    List<Story> getStoryByType(@Param("tagType") TagType tagType);

    @Select(value = "select * from writo.collect where user_id=#{id}")
    List<Integer> getStoryByCollector(@Param("id") Integer id);

    @Select(value = "select * from writo.story where valid is true")
    List<Story> getAllStory();

    @Insert(value = "insert into writo.comment (commenter_id,story_id,content)values(#{comment.commenterId},#{comment.storyId},#{comment.content})")
    void commentStory(@Param("comment") Comment comment);

    @Select(value = "select * from writo.comment where story_id=#{story_id}")
    List<Comment> getComment(@Param("story_id")long story_id);

    @Update(value = "update writo.story set popularity=popularity+#{popularity} where id=#{storyId}")
    void updatePopularity(@Param("popularity")int popularity,@Param("storyId")long storyId);

    @Select(value = "select author_id from writo.story where id=#{storyId}")
    long getAuthorOfStory(@Param("storyId")long storyId);

    @Select(value = "select depth from writo.story where id=#{storyId}")
    int getDepthOfStory(@Param("storyId")long storyId);

}
