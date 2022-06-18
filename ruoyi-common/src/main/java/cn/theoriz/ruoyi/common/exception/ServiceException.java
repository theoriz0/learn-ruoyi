package cn.theoriz.ruoyi.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceException extends RuntimeException{
    public static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private String detailMessage;

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String message, Integer code) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
