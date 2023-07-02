package apptive.fruitable.board.service.inter;

import apptive.fruitable.board.dto.post.PostDto;
import apptive.fruitable.board.dto.post.PostRequestDto;

import java.io.IOException;
import java.util.List;

public interface PostService {

    public Long savePost(PostRequestDto requestDto) throws Exception;
    public List<PostDto> getPostList();
    public PostDto getPost(Long id);
    public void deletePost(Long id);
    public Long update(Long id, PostRequestDto requestDto) throws IOException;
}
