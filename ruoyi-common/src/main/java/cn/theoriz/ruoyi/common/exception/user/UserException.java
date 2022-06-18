package cn.theoriz.ruoyi.common.exception.user;

import cn.theoriz.ruoyi.common.exception.BaseException;

public class UserException extends BaseException {
    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
