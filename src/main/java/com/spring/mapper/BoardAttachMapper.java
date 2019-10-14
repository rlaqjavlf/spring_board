package com.spring.mapper;

import java.util.List;

import com.spring.domain.BoardAttachVO;

public interface BoardAttachMapper {
 public int insert(BoardAttachVO vo);
 
 public int delete(int bno);
 
 public List<BoardAttachVO>findByBno(int bno);
}
