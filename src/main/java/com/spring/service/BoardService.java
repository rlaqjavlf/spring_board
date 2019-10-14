package com.spring.service;


import java.util.List;

import com.spring.domain.BoardAttachVO;
import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardService {

	//글등록
	public void register(BoardVO vo) throws Exception;
	//리스트 불러오기 
	//public List<BoardVO> getList()throws Exception;
	//페이징 기법 적용시
	public List<BoardVO> getList(Criteria cri)throws Exception;
	//페이지 전체 카운트 
	public int getTotalCount(Criteria cri)throws Exception;
	//읽기,수정페이지 이동
	public BoardVO readBoard(int bno) throws Exception;
	//게시글 수정 
	public int modifyBoard(BoardVO vo)throws Exception;
	//게시글 삭제
	public int deleteBoard(int bno)throws Exception;
	//첨부파일 목록 가져오기
	public List<BoardAttachVO> getAttachList(int bno);
	
	
}
