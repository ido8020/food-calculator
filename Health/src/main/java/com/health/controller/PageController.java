package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.health.food.FoodService;
import com.health.food.FoodVO;

@Controller
public class PageController {
	@Autowired
	private FoodService service;
	@GetMapping("/index.do")
	String index() { 
	
		return "/index.html";
	}

	@GetMapping("/index3.do")
	void index3(FoodVO vo ,Model model) { 
		int pageSize=10; //한 페이지에 출력되는 레코드수
		int pageListSize=10;//한 페이지에 선택가능한 숫자 1,2,3,4...10
		
		int totalcount=service.totalCount(vo);
		int totalpage=(int)Math.ceil((double)totalcount/pageSize);
		int start;
		if (vo.getStart() == 0) {
            start = 1;
        } else {
            start = vo.getStart();
        }

		vo.setCh2(vo.getCh2());
		int nowPage = start / pageSize + 1;
	      
		int listStartPage = (nowPage - 1) / pageListSize * pageListSize + 1;
		int listEndPage = listStartPage + pageListSize - 1 ;
		
		vo.setPagesize( pageSize );	
		vo.setStart(start);
		model.addAttribute("li", service.selectAll(vo));		

		model.addAttribute("start", start);
		model.addAttribute("totalcount", totalcount);
		model.addAttribute("totalPage", totalpage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageListSize", pageListSize);
	  
		model.addAttribute("listStartPage", listStartPage);
		model.addAttribute("listEndPage", listEndPage);
		
		model.addAttribute("ch1", vo.getCh1());
		model.addAttribute("ch2", vo.getCh2());
		
	}

	
	
	//에러관련 페이지 
	@GetMapping("/401.do")
	String error401() {
	
		return "/error/401.html";
		
	}
	@GetMapping("/404.do")
	String error404() {
	
		return "/error/404.html";
		
	}
	@GetMapping("/500.do")
	String error500() {
	
		return "/error/500.html";
		
	}
	
	

}
