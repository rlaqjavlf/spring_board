package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardAttachVO;
import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.PageDTO;
import com.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/register")
	public void registerGet() {
		log.info("==게시글 등록 품 요청==");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardVO vo, RedirectAttributes rttr) throws Exception{
		log.info("==게시글 등록==");

		log.info("title : "+vo.getTitle());
		log.info("content : "+vo.getContent());
		log.info("writer : "+vo.getWriter());
			//attachList 확인 
			if(vo.getAttachList() != null) {
				vo.getAttachList().forEach(attach->log.info(""+attach));
			}
			
			
			service.register(vo);
			rttr.addFlashAttribute("result",vo.getBno());
						
			return "redirect:list";
	}
	//board/list => controller 작성
	//db작업 (리스트 가져오기)
	//model에담는다
	@GetMapping("/list")
	public void listGet(Model model, Criteria cri) throws Exception{
		log.info("==게시판 보기==");
		
		List<BoardVO>list = service.getList(cri);
		
		//request 객체
		model.addAttribute("list", list);
		model.addAttribute("pageDTO",new PageDTO(cri,service.getTotalCount(cri)));
	
	}
	//글보기와, 수정페이지 이동 동시
	@GetMapping(value= {"/read","/modify"})
	public void read( int bno, Model model, @ModelAttribute("cri") Criteria cri) throws Exception{
		
		log.info("==게시글 보기== : "+bno);
		log.info("==게시글 보기cri== : "+cri);
		
        BoardVO vo=service.readBoard(bno);
       
        log.info("==vo 담겼냐?=="+vo);
		
		model.addAttribute("vo",vo);
		
		
	}
	//게시글 수정
	@PostMapping("/modify")
	public String modify(BoardVO vo, @ModelAttribute("cri") Criteria cri,RedirectAttributes rttr) throws Exception{
		log.info("==게시글 수정==");
		log.info("title : "+vo.getTitle());
		log.info("content : "+vo.getContent());
		log.info("cri : "+cri);
		
		int rs=service.modifyBoard(vo);
		if(rs==1){
			rttr.addFlashAttribute("result","sussess");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:list";
	}
	//게시글 삭제
	@PostMapping("/remove")
	public String remove(int bno, @ModelAttribute("cri") Criteria cri,RedirectAttributes rttr) throws Exception{
		log.info("==게시글 삭제=="+cri);
		
		int rs=service.deleteBoard(bno);
		
		if(rs==1){
			rttr.addFlashAttribute("result","삭제");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:list";
	}
	
	//첨부물 전체목록 가져오기
	public ResponseEntity<List<BoardAttachVO>> getAttachList(int bno){
		log.info("첨부파일 가져오기");
		return new ResponseEntity<>(service.getAttachList(bno),HttpStatus.OK);
		
	}
}
