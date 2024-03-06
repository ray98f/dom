package com.wzmtr.dom.soft.mdm.orgquery.service.impl;

import com.wzmtr.dom.soft.mdm.orgquery.vo.RequestMessage;
import com.wzmtr.dom.soft.mdm.orgquery.vo.ResponseMessage;

import javax.jws.WebService;

@WebService(targetNamespace = "http://impl.service.orgquery.mdm.soft.com/")
public interface IGetData {

	public ResponseMessage getData(RequestMessage requestMessage);
}
