package com.wzmtr.dom.entity.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/10 15:58
 */
@ApiModel
@Data
public class BaseResponse {

    private static final BaseResponse SUCCESS;

    private static final BaseResponse AUTH_FAILED;

    private Integer code;

    private String message;

    public BaseResponse(AppStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseResponse() {
    }

    public BaseResponse code(Integer code) {
        this.code = code;
        return this;
    }

    public BaseResponse message(String message) {
        this.message = message;
        return this;
    }

    static {
        SUCCESS = new BaseResponse(AppStatus.SUCCESS);
        AUTH_FAILED = new BaseResponse(AppStatus.AUTH_FAILED);
    }

    @Getter
    public enum AppStatus {
        /**
         * 成功
         */
        SUCCESS(0, "success"),
        /**
         * 鉴权失败
         */
        AUTH_FAILED(401, "auth failed");

        private String message;
        private Integer code;

        AppStatus(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

    }

}
