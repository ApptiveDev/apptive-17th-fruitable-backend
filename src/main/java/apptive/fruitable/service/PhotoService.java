package apptive.fruitable.service;

import apptive.fruitable.domain.post.Photo;
import apptive.fruitable.dto.PhotoDto;
import apptive.fruitable.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhotoService {

    private PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {

        this.photoRepository = photoRepository;
    }

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
}
