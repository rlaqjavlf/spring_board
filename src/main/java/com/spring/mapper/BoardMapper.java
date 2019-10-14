package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardMapper {
	
	public void insertSelectKey(BoardVO vo);
	//public List<BoardVO> getList();
	//페이징 기법 적용시
	public List<BoardVO> getList(Criteria cri);
	//페이지 총 개수 카운트
	public int totalCnt(Criteria cri);
	public BoardVO read(int bno);
	public int modify(BoardVO vo);
	public int remove(int bno);
	
	
	//댓글 수 카운트 수 
	public void updateReplyCnt(@Param("bno")int bno, @Param("amount")int amount);
}
