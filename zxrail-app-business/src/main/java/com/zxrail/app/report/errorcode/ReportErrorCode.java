package com.zxrail.app.report.errorcode;

import com.zxrail.framework.common.enums.ErrorCode;
import com.zxrail.framework.common.errorcode.BaseErrorCodeInterface;
import com.zxrail.framework.common.errorcode.ModuleEnum;

public interface ReportErrorCode extends BaseErrorCodeInterface {
    ErrorCode APP_RUNTIME_EXCEPTION_ERROR = new ErrorCode(ModuleEnum.BIZ_MODULE,
        1000_01, "未知运行时异常，请联系管理员确认.{}");
}