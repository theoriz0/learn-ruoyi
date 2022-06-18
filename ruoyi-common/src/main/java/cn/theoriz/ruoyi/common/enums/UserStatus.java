package cn.theoriz.ruoyi.common.enums;

import cn.theoriz.ruoyi.common.exception.ServiceException;

public enum UserStatus {
    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");
    private final String code;
    private final String info;

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }
}
