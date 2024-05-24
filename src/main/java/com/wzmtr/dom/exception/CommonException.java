package com.wzmtr.dom.exception;

import com.wzmtr.dom.enums.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通用异常
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonException extends RuntimeException {

    private Integer code;

    private String message;

    private String[] params;

    public CommonException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonException(ErrorCode error) {
        this.code = error.code();
        this.message = error.message();
    }

    public CommonException(ErrorCode error, String... params) {
        this.code = error.code();
        this.message = error.message();
        this.params = params;
    }

    public CommonException(ErrorCode error, Throwable cause, String message) {
        super(message, cause);
        this.code = error.code();
        this.message = message;
    }

    public CommonException(ErrorCode error, Throwable cause) {
        super(error.message(), cause);
        this.code = error.code();
    }


}
