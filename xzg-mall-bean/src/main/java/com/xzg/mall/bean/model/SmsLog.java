package com.xzg.mall.bean.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("tz_sms_log")
public class SmsLog {
    /**
     * ID
     */
    @TableId

    private Long id;

    /**
     * 用户id
     */

    private String userId;

    /**
     * 手机号码
     */

    private String userPhone;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 手机验证码
     */

    private String mobileCode;

    /**
     * 短信类型  1:注册  2:验证
     */
    private Integer type;

    /**
     * 发送时间
     */

    private Date recDate;

    /**
     * 发送短信返回码
     */

    private String responseCode;

    /**
     * 状态  1:有效  0：失效
     */
    private Integer status;

}