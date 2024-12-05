package fast.boot.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器，一般用来对请求进行过滤
 */
@WebFilter("/*") //过滤器匹配的请求
@Slf4j
@Component
public class FastBootWebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器1");
        //TODO 增加过滤器的逻辑，一般都是token验证之类的
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
