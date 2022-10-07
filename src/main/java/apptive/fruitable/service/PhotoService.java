package apptive.fruitable.service;

import apptive.fruitable.domain.post.Photo;
import apptive.fruitable.domain.post.QPhoto;
import apptive.fruitable.dto.PhotoDto;
import apptive.fruitable.dto.PhotoResponseDto;
import apptive.fruitable.repository.PhotoRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    private PhotoRepository photoRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Long saveFile(PhotoDto photoDto) {

        return photoRepository.save(photoDto.toEntity()).getId();
    }

    @Transactional
    public PhotoDto getFile(Long id) {

        Photo photo = photoRepository.findById(id).get();

        PhotoDto photoDto = PhotoDto.builder()
                .id(id)
                .origFilename(photo.getOrigFilename())
                .filePath(photo.getFilePath())
                .build();

        return photoDto;
    }

    /**
     * 이미지 전체 조회
     */
    @Transactional(readOnly = true)
    public List<PhotoResponseDto> findAllByPost(Long id) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QPhoto photo = QPhoto.photo;

        List<Photo> photoList = queryFactory
                .selectFrom(photo)
                .where(photo.post.id.eq(id))
                .fetch();

        return photoList.stream()
                .map(PhotoResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 이미지 삭제
     */
    public void deletePhoto(Long id) {

        photoRepository.deleteById(id);
    }

    /**
     * 개별 이미지 조회
     */
    @Transactional(readOnly = true)
    public PhotoDto findByFileId(Long id) {

        Photo entity = photoRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 파일이 존재하지 않습니다."));

        PhotoDto photoDto = new PhotoDto(
                entity.getId(),
                entity.getOrigFilename(),
                entity.getFilePath(),
                entity.getFileSize());

        return photoDto;
    }
}
