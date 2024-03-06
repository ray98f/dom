package com.wzmtr.dom.soft.ipl.todoList.vo;

import lombok.Data;

@Data
public class SearchRequestMessage {
	String verb;
	String noun;
	User user;
	SearchMessage message;
}
