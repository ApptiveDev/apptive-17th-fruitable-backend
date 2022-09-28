package apptive.fruitable.service;

import apptive.fruitable.domain.post.PostImg;
import apptive.fruitable.repository.PostImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostImgService {

    @Value("${img.location}")
    private String postImgLocation;
    private final PostImgRepository postImgRepository;
    private final FileService fileService;

    public void savePostImg(PostImg postImg, MultipartFile imgFile) throws Exception {
        String oriImgName = imgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(postImgLocation, oriImgName,
                    imgFile.getBytes());
            imgUrl = "/img/post/" + imgName;
        }

        //상품 이미지 정보 저장
        postImg.imgUpdate(oriImgName, imgName, imgUrl);
        postImgRepository.save(postImg);
    }

    public void updatePostImg(Long postImgId, MultipartFile postImgFile) throws Exception {
        if(!postImgFile.isEmpty()) {
            //id를 이용하여 기존에 저장했던 상품 이미지 엔티티 조회
            PostImg savedPostImg = postImgRepository.findById(postImgId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 등록된 이미지 파일이 있을 경우 삭제
            if(!StringUtils.isEmpty(savedPostImg.getImgName())) {
                fileService.deleteImg(postImgLocation+"/"+savedPostImg.getImgName());
            }

            String oriImgName = postImgFile.getOriginalFilename(); //실제 로컬에 저장된 상품 이미지 파일의 이름
            String imgName = fileService.uploadFile(postImgLocation, oriImgName, postImgFile.getBytes()); //업로드했던 상품 이미지 파일의 원래 이름
            String imgUrl = "/img/" + imgName; //업로드 결과 로컬에 저장된 상품 이미지 파일 불러오는 경로
            savedPostImg.imgUpdate(oriImgName, imgName, imgUrl);
        }
    }
}
