package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;

public interface ReplyMapper {
	//mapper.xml 에 커리문 실행시키기위해 보냄
	public int replyinsert(ReplyVO vo);
	
	public ReplyVO replyRead(int rno);
	
	public List<ReplyVO> getList(@Param("cri")Criteria cri,@Param("bno") int bno);

	public int update(ReplyVO vo);
	
	public int delete(int rno);
	
	public int getCountByBno(int bno);

}
