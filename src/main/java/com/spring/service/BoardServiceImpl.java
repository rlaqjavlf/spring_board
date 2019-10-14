package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.domain.BoardAttachVO;
import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.mapper.BoardAttachMapper;
import com.spring.mapper.BoardMapper;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardMapper mapper;
	@Inject
	private BoardAttachMapper attachMapper;
	@Override
	public void register(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		mapper.insertSelectKey(vo);
		//파일첨부 후 추가 
		if(vo.getAttachList()==null || vo.getAttachList().size()<=0) {
			return;
		}
		vo.getAttachList().forEach(attach->{
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
	}
/*
	@Override
	public List<BoardVO> getList() throws Exception{
		// TODO Auto-generated method stub
		return mapper.getList();
	}
*/
	@Override
	public List<BoardVO> getList(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getList(cri);
	}
	@Override
	public int getTotalCount(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return mapper.totalCnt(cri);
	}
	
	@Override
	public BoardVO readBoard(int bno) throws Exception{
		// TODO Auto-generated method stub
		return mapper.read(bno);
	}

	@Override
	public int modifyBoard(BoardVO vo) throws Exception{
		// TODO Auto-generated method stub
		return mapper.modify(vo);
		
	}

	@Override
	public int deleteBoard(int bno) throws Exception{
		// TODO Auto-generated method stub
		return mapper.remove(bno);
		
	}
	
	@Override
	public List<BoardAttachVO> getAttachList(int bno) {
		// TODO Auto-generated method stub
		return attachMapper.findByBno(bno);
	}

	
	
	
}
