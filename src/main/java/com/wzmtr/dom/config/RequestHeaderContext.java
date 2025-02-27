package com.wzmtr.dom.config;

import com.wzmtr.dom.entity.CurrentLoginUser;
import lombok.Data;

/**
 * RequestHeader配置
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
public class RequestHeaderContext {


    private static final ThreadLocal<RequestHeaderContext> REQUEST_HEADER_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    private CurrentLoginUser user;

    public static RequestHeaderContext getInstance() {
        return REQUEST_HEADER_CONTEXT_THREAD_LOCAL.get();
    }

    public void setContext(RequestHeaderContext context) {
        REQUEST_HEADER_CONTEXT_THREAD_LOCAL.set(context);
    }

    public static void clean() {
        REQUEST_HEADER_CONTEXT_THREAD_LOCAL.remove();
    }

    private RequestHeaderContext(RequestHeaderContextBuild requestHeaderContextBuild) {
        this.user = requestHeaderContextBuild.user;
        setContext(this);
    }

    @Data
    public static class RequestHeaderContextBuild {

        private CurrentLoginUser user;

        public RequestHeaderContextBuild user(CurrentLoginUser user) {
            this.user = user;
            return this;
        }

        public RequestHeaderContext build() {
            return new RequestHeaderContext(this);
        }
    }

}
