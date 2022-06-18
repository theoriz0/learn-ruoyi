package cn.theoriz.ruoyi.common.exception.user;

public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1823L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
