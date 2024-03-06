package com.wzmtr.dom.soft.mdm.extraorgquery.service.impl;

import com.wzmtr.dom.soft.mdm.extraorgquery.vo.RequestMessage;
import com.wzmtr.dom.soft.mdm.extraorgquery.vo.ResponseMessage;

import javax.jws.WebService;

@WebService(targetNamespace = "http://impl.service.extraorgquery.mdm.soft.com/")
public interface IGetData {

	public ResponseMessage getData(RequestMessage requestMessage);
}
