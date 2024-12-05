package fast.boot.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class SyncRoleInfoTask implements FastBootCommonTask{
    @Override
    public void execute(Map<String, Object> params) {
        log.info("SyncRoleInfoTask execute");
    }
}
