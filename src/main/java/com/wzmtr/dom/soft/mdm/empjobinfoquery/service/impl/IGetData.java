package com.wzmtr.dom.soft.mdm.empjobinfoquery.service.impl;

import com.wzmtr.dom.soft.mdm.empjobinfoquery.vo.RequestMessage;
import com.wzmtr.dom.soft.mdm.empjobinfoquery.vo.ResponseMessage;

import javax.jws.WebService;

@WebService(targetNamespace = "http://impl.service.empjobinfoquery.mdm.soft.com/")
public interface IGetData {

	public ResponseMessage getData(RequestMessage requestMessage);
}
