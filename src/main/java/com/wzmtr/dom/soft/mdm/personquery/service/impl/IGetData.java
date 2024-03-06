package com.wzmtr.dom.soft.mdm.personquery.service.impl;



import com.wzmtr.dom.soft.mdm.personquery.vo.RequestMessage;
import com.wzmtr.dom.soft.mdm.personquery.vo.ResponseMessage;

import javax.jws.WebService;

@WebService(targetNamespace = "http://impl.service.personquery.mdm.soft.com/")
public interface IGetData {

	public ResponseMessage getData(RequestMessage requestMessage);
}
