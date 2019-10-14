package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageDTO;
import com.spring.domain.ReplyVO;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;

@Service("ReplyService")
public class ReplyServiceImpl implements ReplyService {

	@Inject
	public ReplyMapper mapper;
	@Inject
	public BoardMapper boardMapper;
	
	@Transactional
	@Override
	public boolean register(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.updateReplyCnt(vo.getBno(),+1);
		return mapper.replyinsert(vo)==1?true:false;
	}

	@Override
	public ReplyVO read(int rno) throws Exception {
		// TODO Auto-generated method stub
		return mapper.replyRead(rno);
	}
	/*
	@Override
	public List<ReplyVO> replyList(Criteria cri,int bno) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getList(cri, bno);
		
	}
	*/
	@Override
	public ReplyPageDTO replyList(Criteria cri,int bno) throws Exception {
		// TODO Auto-generated method stub
		return new ReplyPageDTO(mapper.getCountByBno(bno) ,mapper.getList(cri, bno)) ;
		
	}

	@Override
	public boolean modify(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.update(vo)==1?true:false;
		
	}
	@Transactional
	@Override
	public boolean delete(int rno) throws Exception {
		// TODO Auto-generated method stub
		ReplyVO vo = mapper.replyRead(rno);
		boardMapper.updateReplyCnt(vo.getBno(),-1);
		return mapper.delete(rno)==1?true:false;
	}

}
