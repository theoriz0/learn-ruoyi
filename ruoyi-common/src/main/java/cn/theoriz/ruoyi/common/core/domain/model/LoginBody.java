package cn.theoriz.ruoyi.common.core.domain.model;

import lombok.Data;

@Data
public class LoginBody {
    private String username;
    private String password;
    private String code;
    private String uuid;
}
