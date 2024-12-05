package fast.boot.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileObsService {

    public void uploadFile(MultipartFile file);
}
