package apptive.fruitable.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service @Log
public class FileService {

    public String uploadFile(String uploadPath, String oriImgName, byte[] imgData) throws Exception {
        UUID uuid = UUID.randomUUID();
        String extension = oriImgName.substring(oriImgName.lastIndexOf("."));
        String savedImgName = uuid.toString() + extension;
        String imgUploadUrl = uploadPath + "/" + savedImgName;
        FileOutputStream fos = new FileOutputStream(imgUploadUrl);
        fos.write(imgData);
        fos.close();

        return savedImgName;
    }

    public void deleteImg(String imgPath) throws Exception {
        File deleteImg = new File(imgPath);
        if(deleteImg.exists()) {
            deleteImg.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
