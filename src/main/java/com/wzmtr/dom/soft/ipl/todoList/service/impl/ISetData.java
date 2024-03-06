package com.wzmtr.dom.soft.ipl.todoList.service.impl;

import com.wzmtr.dom.soft.ipl.todoList.vo.RequestMessage;
import com.wzmtr.dom.soft.ipl.todoList.vo.ResponseMessage;

import javax.jws.WebService;


@WebService(targetNamespace = "http://impl.service.todoList.ipl.soft.com/")
public interface ISetData {
	
	public ResponseMessage setData(RequestMessage requestMessage);
	
}
