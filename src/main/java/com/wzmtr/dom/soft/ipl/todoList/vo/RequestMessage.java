package com.wzmtr.dom.soft.ipl.todoList.vo;

import lombok.Data;

@Data
public class RequestMessage {
	String verb;
	String noun;
	User user;
	Message message;
}
