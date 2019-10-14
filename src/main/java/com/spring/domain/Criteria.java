package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum; // 페이지 번호 
	private int amount; // 한페이지당 보여주는 게시물
	
	//list에서 조건 검색부분
	private String type;// 검색조건
	private String keyword; //검색어
	
	public Criteria() {
		//기본값 세팅
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {

		this.pageNum = pageNum;
		this.amount = amount;
	}
	//건색조건을 String 배열로 만들어 리턴하는것이 목작이다
	public String[] getTypeArr() {
		//type => TCW => {'T','C','W'}
		return type ==null? new String[] {}:type.split("");
		
	}
	
}
