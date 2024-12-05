package fast.boot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "fast.boot.controller")
@Slf4j
@ResponseBody//这里需要使用ResponseBody注解来输出，这里需要注意controller层需要用RestController注解
public class FastBootExceptionHandler {

    /**
     * 处理 spring-validation抛出的MethodArgumentNotValidException
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        log.info(e.getMessage());
        return "aaaa";
    }

    /**
     * 处理 spring-validation抛出的MethodArgumentNotValidException
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public void commonExceptionHandler(Exception e){
        //log.info(e.getMessage());
        log.info("bbb");
        //return "bbb";
    }
}
