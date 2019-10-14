package com.spring.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private Date reqdate;
	private Date updatedate;
	private int replyCnt;

	//첨부파일 목록 가져오기 
	private List<BoardAttachVO> attachList;
	
}
