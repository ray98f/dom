package com.wzmtr.dom.config.handle;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.entity.CurrentLoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class TokenHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    public static final String CURRENT_TOKEN_KEY = "tokenInfo";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(CurrentLoginUser.class) && parameter.hasParameterAnnotation(CurrUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

        return request.getAttribute(CURRENT_TOKEN_KEY, RequestAttributes.SCOPE_REQUEST);
    }
}
