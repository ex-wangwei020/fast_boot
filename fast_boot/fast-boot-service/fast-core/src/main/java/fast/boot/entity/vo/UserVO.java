package fast.boot.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data //自动构造set(),get()方法
@NoArgsConstructor //自动构造无参数函数
@AllArgsConstructor //自动构造带参数的函数
@Builder//自动构造builer模式
public class UserVO {

    @NotNull
    private String id;

    //message设置校验不通过的提示信息
    @NotBlank(message = "用户名不能为空")
    private String userName;

    //正则校验-手机号校验
    @Pattern(regexp="^((0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?)$|^(0?1[3578][0-9]{9})$|^(400[0-9]{7})$") //正则校验
    private String userPhone;

    //数值校验
    @Max(value = 60, message = "未成年或者退休人员")
    @Min(value = 18, message = "未成年或者退休人员")
    private long age;

    @Size(max = 32, min = 3, message = "长度大于3小于32")
    private String um;

    //collection校验
    @NotEmpty
    private List<String> bankCard;
}
