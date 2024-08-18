package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.health.member.MemberService;
import com.health.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	@GetMapping("/insertMember.do")
	String memberInsert(MemberVO vo) {
		
		
		service.memberInsert(vo);
		return "/index.do";
		
	}
	@GetMapping("/memberList.do")
	String memberList(Model model,HttpSession session) {
	    
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	    
	    if (loginUser == null || !"admin".equals(loginUser.getRole())) {
	   	        return "redirect:/index.do";
	    }
	    model.addAttribute("li", service.memberList());
		return "/member/memberList.html";
	
	}
	@GetMapping("/memberUpdate.do")
	String memberUpdate(Model model,MemberVO vo) {
		
		service.memberUpdate(vo);
		
		return "redirect:/memberList.do";
		
	}
	@GetMapping("/memberEdit.do")
	String memberEdit(Model model,MemberVO vo,HttpSession session) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	    
	    if (loginUser == null || !"admin".equals(loginUser.getRole())) {
	   	        return "redirect:/index.do";
	    }
		model.addAttribute("m",service.memberListOne(vo));
		return "/member/memberEdit.html";
		
	}

}
