package com.wzmtr.dom.soft.ipl.newsnotify.service.impl;

import com.wzmtr.dom.soft.ipl.newsnotify.vo.RequestMessage;
import com.wzmtr.dom.soft.ipl.newsnotify.vo.ResponseMessage;

import javax.jws.WebService;

@WebService(targetNamespace = "http://impl.service.newsnotify.ipl.soft.com/")
public interface ISetData {
	
	public ResponseMessage setData(RequestMessage requestMessage);
	
}
