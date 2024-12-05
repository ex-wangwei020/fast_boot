package fast.boot.controller;

import com.google.common.collect.Lists;
import fast.boot.entity.vo.UserVO;
import fast.boot.service.FileObsService;
import fast.boot.task.FastBootCommonTask;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class FastBootController {

    @Resource(name = "${file.obs.service}")
    private FileObsService fileObsService;

    @Resource
    private Map<String, FastBootCommonTask> tasks;


    @RequestMapping(method = RequestMethod.POST, value = "/user/info")
    public void getUserInfo(@RequestBody @Validated UserVO userVO1){
        fileObsService.uploadFile(null);
        List<String> executeTaskList= Lists.newArrayList("syncUserInfoTask");
        tasks.keySet().stream().filter(key->!executeTaskList.contains(key)).forEach(key->
                tasks.get(key).execute(null));
    }
}
