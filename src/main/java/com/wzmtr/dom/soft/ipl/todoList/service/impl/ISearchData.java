package com.wzmtr.dom.soft.ipl.todoList.service.impl;

import com.wzmtr.dom.soft.ipl.todoList.vo.ResponseMessage;
import com.wzmtr.dom.soft.ipl.todoList.vo.SearchRequestMessage;

import javax.jws.WebService;


@WebService()
public interface ISearchData {
	
	public ResponseMessage searchData(SearchRequestMessage requestMessage);
	
}
