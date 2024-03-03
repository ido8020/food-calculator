package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rubypaper.guestbook.GuestbookService;
import com.rubypaper.guestbook.GuestbookVO;
import com.rubypaper.member.MemberService;
import com.rubypaper.member.MemberVO;
import com.rubypaper.money.MoneyService;
import com.rubypaper.money.MoneyVO;


@Controller
public class RubypaperController {
	@Autowired
	private MemberService  service;
	@Autowired
	private MoneyService service2;
	@Autowired
	private GuestbookService service3;
	
	RubypaperController (){
		System.out.println("==>RubypaperController");
	}
	@GetMapping("/MemberList.do")
	String MemberList(MemberVO vo, Model  model) {
		model.addAttribute("li", service.MemberList(vo));		

		return "/member/memberList.html";
		
	}
	@GetMapping("/Member.do")
	String Member(MemberVO vo, Model  model) {
		Date toDay =new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		String toDayStr=df.format(toDay);
		model.addAttribute("toDayStr",toDayStr);
		model.addAttribute("custno", service.custno());		

		return "/member/member.html";
	}
	@PostMapping("/MemberInsert.do")
	String MemberInsert(MemberVO vo, Model  model) {
	String address = vo.getPostcode()+" "
					+vo.getRoadAddress()+" "
					+vo.getDetailAddress()+" "
					+vo.getExtraAddress();
		
		vo.setAddress(address);
		service.insert(vo);
		
		return "redirect:/MemberList.do";
	}
	@GetMapping("/memberEdit.do")
	 String memberList(Model model, MemberVO  vo ) {	
		model.addAttribute("m", service.edit(vo));
	   return "/member/memberEdit.html";		
	}

	@PostMapping("/MemberUpdate.do")
	 String MemberUpdate(MemberVO  vo) {

		 String address = vo.getPostcode() + " : " + vo.getRoadAddress() + " " +vo.getDetailAddress()+ " " + vo.getExtraAddress();
		 vo.setAddress(address);
		 
		 System.out.println("===> MemberUpdate.do :" + vo);
		 
		 service.update(vo);
		
	   return "redirect:/MemberList.do";		
	}
	
	//money 관련 파트
	@GetMapping("/MoneyList.do")
	String MoneyList(MoneyVO vo, Model  model) {
		model.addAttribute("li",service2.MoneyList(vo));
	
		return "/money/moneylist.html";
	}
	
	//방명록 관련 파트
	@GetMapping("/guestbook.do")
	String guestbook(GuestbookVO vo) {
		for(int i=1; i<=100; i++) {
			int nameR=(int)(Math.random()*10);
			int memoR=(int)(Math.random()*10);
			
			String[] nameStr= {"영심이","하늘이","똘이","구름","가나다","라마바","사아자","차카파","짬뽕","피자"};

			String[] memoStr= {"반갑습니다","안녕하세요","123","456","789","10","1889","6333","4445","7785"};
			vo.setName(nameStr[nameR]);
			vo.setMemo(memoStr[memoR]);

			service3.insert100(vo);
		}
		
		
		return "redirect:/guestbookList.do";
	}
	@GetMapping("/guestbookList.do")
	String guestbookList(GuestbookVO vo, Model  model) {
		int pageSize=10; //한 페이지에 출력되는 레코드수
		int pageListSize=10;//한 페이지에 선택가능한 숫자 1,2,3,4...10
		
		int totalcount=service3.totalcount(vo);
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
		 
		
		model.addAttribute("li", service3.GuestbookList(vo));
			
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
		
		return "/guestbook/guestbookList.html";
	}
}