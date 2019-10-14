package com.spring.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor 

public class ReplyPageDTO {
		private int replyCnt; //게시글 전체 댓글의 개수
		private List<ReplyVO> list; //게시글의 전체 댓글의 내용
		
}
