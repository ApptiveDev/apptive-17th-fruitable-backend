package apptive.fruitable.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileUploadService {

    @RequestMapping(value = "/upload")
    public String restore(MultipartHttpServletRequest files) {
        String path = "/Users/hjhwang/Projects/Spring/image";
        List<MultipartFile> list = files.getFiles("files");

        for (MultipartFile mf : list) {

            String originFileName = mf.getOriginalFilename();
            //long fileSize = mf.getSize();

            String fileExtension = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());

            UUID uuid = UUID.randomUUID();
            //System.out.println(uuid.toString());
            String[] uuids = uuid.toString().split("-");

            String uniqueName = uuids[0];
            //System.out.println("생성된 고유 문자열 : " + uniqueName);
            //System.out.println("확장자명 : " + fileExtension);

            File saveFile = new File(path + "\\" + uniqueName + fileExtension);

            //System.out.println("파일 명 : " + originFileName);
            //System.out.println("용량크기(byte) : " + fileSize);
            try {
                mf.transferTo(saveFile);
            } catch (IllegalStateException | IOException e) {
                System.out.println("파일 업로드 실패");
                e.printStackTrace();
            }
        }
        return "redirect:/upload";
    }
}
