package writo.terminal.core.mapperI;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import writo.terminal.data.Comment;
import writo.terminal.data.Story;
import writo.terminal.data.StoryContent;
import writo.terminal.type.TagType;

import java.util.List;

@Mapper
@Component
public interface StoryMapper {

    @Update("insert into writo.story_content (content) values(#{content})")
    void uploadStoryContent(@Param("content") String content);

    @Update("insert into writo.story (author_id,title,father_id,tag,depth) values " +
            "(#{story.authorId} ,#{story.title} ,#{story.fatherId} ,#{story.tag},#{story.fatherId}  ) ")
    @Options(useGeneratedKeys = true, keyProperty = "story.id", keyColumn = "id")
    void insert(@Param("story") Story story);

    @Update("update writo.story set father_id=#{story.fatherId}, author_id=#{story.authorId}, title=#{story.title} ," +
            "tag=#{story.tag}, valid=#{story.valid}, open=#{story.open} ,popularity=#{story.popularity} ," +
            "depth=#{story.depth}, tree_id=#{story.treeId} ,path=#{story.path} where id=#{story.id} ")
    void update(@Param("story") Story story);

    @Update("update writo.story_content set content=#{content} where id=#{id}")
    void deleteStoryContent(@Param("id") long id, @Param("content") String content);

    @Update("update writo.story set valid=#{valid} where id=#{id}")
    void deleteStory(@Param("id") long id, @Param("valid") boolean valid);

    @Select("select * from writo.story where id=#{id} ")
    Story getStoryById(@Param("id") Long id);

    @Select("select * from writo.story_content where id=#{id}")
    StoryContent getStoryContentById(@Param("id") long id);

    @Select("select * from writo.story where author_id=#{author_id} and valid=true")
    List<Story> getStoryByAuthor(@Param("author_id") long author_id);

    @Select("select * from writo.story where tag=#{tagType} and valid=true")
    List<Story> getStoryByType(@Param("tagType") TagType tagType);

    @Select("select * from writo.collect where user_id=#{id}")
    List<Long> getStoryByCollector(@Param("id") long id);

    @Select("select * from writo.story where valid is true")
    List<Story> all();

    @Update("update writo.story set popularity=popularity+#{popularity} where id=#{storyId}")
    void updatePopularity(@Param("popularity") int popularity, @Param("storyId") long storyId);

    @Select("select author_id from writo.story where id=#{storyId}")
    long getAuthorOfStory(@Param("storyId") long storyId);

    @Select("select depth from writo.story where id=#{storyId}")
    int getDepthOfStory(@Param("storyId") long storyId);

}
