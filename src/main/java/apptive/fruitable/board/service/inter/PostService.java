package apptive.fruitable.board.service.inter;

import apptive.fruitable.board.dto.post.PostResponseDto;
import apptive.fruitable.board.dto.post.PostRequestDto;

import java.io.IOException;
import java.util.List;

public interface PostService {

    public Long savePost(PostRequestDto requestDto) throws Exception;
    public List<PostResponseDto.GetDto> getPostList();
    public PostResponseDto.GetWithCommentDto getPost(Long id);
    public void deletePost(Long id);
    public Long update(Long id, PostRequestDto requestDto) throws IOException;
}
