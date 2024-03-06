package com.wzmtr.dom.soft.mdm.personplusquery.service.impl;

import com.wzmtr.dom.soft.mdm.personplusquery.vo.RequestMessage;
import com.wzmtr.dom.soft.mdm.personplusquery.vo.ResponseMessage;

import javax.jws.WebService;

@WebService(targetNamespace = "http://impl.service.personplusquery.mdm.soft.com/")
public interface IGetData {

	public ResponseMessage getData(RequestMessage requestMessage);
}
