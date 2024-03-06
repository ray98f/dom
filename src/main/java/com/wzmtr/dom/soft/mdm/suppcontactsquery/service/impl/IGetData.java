package com.wzmtr.dom.soft.mdm.suppcontactsquery.service.impl;


import com.wzmtr.dom.soft.mdm.suppcontactsquery.vo.RequestMessage;
import com.wzmtr.dom.soft.mdm.suppcontactsquery.vo.ResponseMessage;

import javax.jws.WebService;

@WebService(targetNamespace = "http://impl.service.suppcontactsquery.mdm.soft.com/")
public interface IGetData {

	public ResponseMessage getData(RequestMessage requestMessage);
}
