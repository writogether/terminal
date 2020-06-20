package writo.terminal.view;

import lombok.Data;
import writo.terminal.contract.View;
import writo.terminal.data.Story;
import writo.terminal.type.TagType;

@Data
public class StoryView implements View<Story> {

    private Long id;
    private Long fatherId;
    private Long authorId;
    private String userName;
    private String title;
    private String content;
    private TagType tag;
    private Boolean valid;
    private Boolean open;
    private Integer popularity;
    private Integer depth;
    private String rootTitle;
    private String description;

}
