package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.health.member.MemberService;
import com.health.member.MemberVO;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

	@Autowired
	private MemberService service;
	
	//로그인
	@GetMapping("/login.do")
	String login() { 
		
		return "/login/login.html";
	}
	@PostMapping("/loginOK.do")
	String loginOK(@RequestParam String id, @RequestParam String password,Model model,HttpSession session){
		MemberVO vo=new MemberVO();
		vo.setId(id);
        vo.setPassword(password);

        MemberVO result = service.loginOK(vo);
        
        if (result != null) {
            // 로그인 성공 시
        	System.out.println(result);
          	session.setAttribute("loginUser", result);
       	    
          	return "redirect:/index.do";
       	    
        } else {
            // 로그인 실패 시
        	model.addAttribute("loginF","아이디와 비밀번호를 확인해주세요");
            return "/login/login.html";
        }
		
	}
	//로그아웃
	@GetMapping("logout.do")
	String logout(HttpSession session) {		
		//세션 무효로 로그아웃
		session.invalidate();
		return "redirect:/index.do";			
	}
	
	//회원가입
	@GetMapping("/register.do")
	String register(@RequestParam(required = false) String message, Model model) {
		 if (message != null) {
	            model.addAttribute("registerError", message);
	        }
		return "/login/register.html";
			
	}
	//회원가입 완료
	@GetMapping("/accountOK.do")
	String accountOK(MemberVO vo, Model model) {
		try {
            // 아이디 중복 체크
            if (service.isIdExists(vo.getId())) {
                // 쿼리 파라미터로 에러 메시지를 전달
                return "redirect:/register.do?message=" + encodeURIComponent("이미 존재하는 아이디입니다.");
            }

            // 회원가입 처리
            service.memberInsert(vo);
            return "/login/accountOK.html";
        } catch (RuntimeException e) {
            e.printStackTrace(); // 로그에 에러를 기록합니다.
            return "redirect:/register.do?message=" + encodeURIComponent("회원가입 처리 중 오류가 발생했습니다.");
        }

}
	private String encodeURIComponent(String value) {
		 try {
	            return java.net.URLEncoder.encode(value, "UTF-8");
	        } catch (Exception e) {
	            return value;
	        }
	}
}