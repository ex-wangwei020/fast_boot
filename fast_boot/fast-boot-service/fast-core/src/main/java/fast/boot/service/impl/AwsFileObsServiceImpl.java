package fast.boot.service.impl;

import fast.boot.service.FileObsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class AwsFileObsServiceImpl implements FileObsService {
    @Override
    @SneakyThrows//注解异常
    public void uploadFile(MultipartFile file) {
        log.info("调用AWS去上传文件");
        throw new RuntimeException("111");
    }
}
