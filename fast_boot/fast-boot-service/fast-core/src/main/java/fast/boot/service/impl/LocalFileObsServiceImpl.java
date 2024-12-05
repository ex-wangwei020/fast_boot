package fast.boot.service.impl;

import fast.boot.service.FileObsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class LocalFileObsServiceImpl implements FileObsService {
    @Override
    public void uploadFile(MultipartFile file) {
        log.info("本地保存文件");
    }
}
