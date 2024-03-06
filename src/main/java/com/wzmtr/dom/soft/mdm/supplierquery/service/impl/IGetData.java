package com.wzmtr.dom.soft.mdm.supplierquery.service.impl;

import com.wzmtr.dom.soft.mdm.supplierquery.vo.RequestMessage;
import com.wzmtr.dom.soft.mdm.supplierquery.vo.ResponseMessage;

import javax.jws.WebService;

@WebService(targetNamespace = "http://impl.service.supplierquery.mdm.soft.com/")
public interface IGetData {

	public ResponseMessage getData(RequestMessage requestMessage);
}
