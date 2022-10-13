package apptive.fruitable.controller;

import apptive.fruitable.domain.post.PostFileVO;
import apptive.fruitable.dto.PhotoDto;
import apptive.fruitable.dto.PhotoResponseDto;
import apptive.fruitable.service.PhotoService;
import apptive.fruitable.service.PostService;
import apptive.fruitable.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PhotoService photoService;

    /**
     * postDtoList를 "board/list"에 postList로 전달
     * @param model
     * @return
     */
    /*@GetMapping("/")
    public String list(Model model) {

        List<PostDto> postDtoList = postService.getPostList();
        model.addAttribute("postList", postDtoList);
        return "board/list";
    }*/

    /*
     * 글쓰는 페이지로 이동
     */
    @GetMapping("/post")
    public String post(Model model) {

        model.addAttribute("postDto", new PostDto());
        return "board/post";
    }

    /**
     * Post로 받은 데이터를 데이터베이스에 추가
     * @return 원래 화면
     */
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public String write(@Valid PostDto postDto, BindingResult bindingResult,
                      Model model, @RequestParam("photoFile") List<MultipartFile> photoFileList) throws Exception {

        if(bindingResult.hasErrors()) {
            return "board/post";
        }

        if(photoFileList.get(0).isEmpty() && postDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "board/post";
        }

        try {
            postService.savePost(postDto, photoFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "board/post";
        }

        return "redirect:/";
    }

    /**
     * 각 게시글 클릭시 /post/1 과 같이 get 요청, 해당 아이디의 데이터가 view로 전달되도록 함
     * @param id
     * @param model
     * @return
     */
    /*@GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {

        PostDto postDto = postService.getPost(id);
        model.addAttribute("post", postDto);
        return "board/detail";
    }*/

    /**
     * id에 해당하는 게시글을 수정할 수 있음
     * @param id
     * @param model
     * @return put 형식으로 /post/edit/{id}로 서버에 요청 감
     */
    /*@GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {

        PostDto postDto = postService.getPost(id);
        model.addAttribute("post", postDto);
        return "board/edit";
    }*/

    /**
     * 서버에 put 요청이 오면, 데이터베이스에 변경된 데이터를 저장함
     * @param id, postFileVO
     * @return
     */
    /*@PutMapping("/post/edit/{id}")
    @CrossOrigin
    public String update(@PathVariable Long id, PostFileVO postFileVO) throws Exception {

        PostDto postDto =
                PostDto.builder()
                        .userId(postFileVO.getUserId())
                        .title(postFileVO.getTitle())
                        .contact(postFileVO.getContact())
                        .vege(postFileVO.getVege())
                        .content(postFileVO.getContent())
                        .price(postFileVO.getPrice())
                        .endDate(postFileVO.getEndDate())
                        .build();

        //DB에 저장되어 있는 파일 불러오기
        List<PhotoResponseDto> dbPhotoList = photoService.findAllByPost(id);
        //전달되어온 파일들
        List<MultipartFile> multipartFileList = postFileVO.getFiles();
        //새롭게 전달되어온 파일들의 목록을 저장할 List 선언
        List<MultipartFile> addFileList = new ArrayList<>();

        if(CollectionUtils.isEmpty(dbPhotoList)) { //DB에 아예 존재 X
            if(!CollectionUtils.isEmpty(multipartFileList)) { //전달되어온 파일이 하나라도 존재
                for(MultipartFile multipartFile : multipartFileList)
                    addFileList.add(multipartFile); //저장할 파일 목록에 추가
            }
        } else { //DB에 한장 이상 존재
            if(CollectionUtils.isEmpty(multipartFileList)) { //전달되어온 파일 아예 X
                //파일 삭제
                for(PhotoResponseDto dbPhoto : dbPhotoList)
                    photoService.deletePhoto(dbPhoto.getFileId());
            } else { //전달되어온 파일 한 장 이상 존재
                //DB에 저장되어있는 파일 원본명 목록
                List<String> dbOriginalNameList = new ArrayList<>();

                //DB의 파일 원본명 추출
                for(PhotoResponseDto dbPhoto : dbPhotoList) {
                    //file id로 DB에 저장된 파일 정보 얻어오기
                    PhotoDto dbPhotoDto = photoService.findByFileId(dbPhoto.getFileId());
                    //DB의 파일 원본명 얻어오기
                    String dbOrigFileName = dbPhotoDto.getOrigFilename();

                    if(!multipartFileList.contains(dbOrigFileName)) //서버에 저장된 파일들 중 전달되어온 파일이 존재하지 않는다면
                        photoService.deletePhoto(dbPhoto.getFileId());
                    else
                        dbOriginalNameList.add(dbOrigFileName); //DB에 저장할 파일 목록에 추가
                }

                for(MultipartFile multipartFile : multipartFileList) { //전달되어온 파일 하나씩 검사
                    //파일 원본명 얻어오기
                    String multipartOrigName = multipartFile.getOriginalFilename();
                    if(!dbOriginalNameList.contains(multipartOrigName)) //DB에 없는 파일이면
                        addFileList.add(multipartFile); //DB에 저장할 파일 목록에 추가
                }
            }
        }

        //postService.update(id, postDto, addFileList);

        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {

        postService.deletePost(id);
        return "redirect:/";
    }*/
}
