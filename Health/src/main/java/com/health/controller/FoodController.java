package com.health.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.health.food.FoodService;
import com.health.food.FoodVO;
import com.health.member.MemberVO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class FoodController {
	@Autowired
	private FoodService service;

	@GetMapping("/foodCal.do")
	String foodCal(FoodVO vo, Model  model) {
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
		
		return "/food/foodCal.html";
		
	}
	@GetMapping("/foodCalOK.do")
	void foodCalOK(Model model, @RequestParam String foodcd,
			HttpServletResponse response,HttpSession session) throws Exception {
		MemberVO memberVO=  (MemberVO)session.getAttribute("loginUser");
		int  memberidx = memberVO.getMemberidx();
		
		System.out.println(memberidx);
		
		FoodVO food = service.selectOne(foodcd);
		food.setMemberidx(memberidx);
		
		service.calInsert(food);
		
		if (food != null) {
	        if (food.getMemberidx() == 0) {
	        	food.setMemberidx(10001);
	        	service.calInsert(food);
	        }
	  
	       
	        PrintWriter out = response.getWriter();
	        out.print("success");
	        
	    }
	}
	
	
	@GetMapping("/foodCalEdit.do")
	String foodEdit(Model model ,FoodVO vo,HttpSession session) {
		MemberVO memberVO=  (MemberVO)session.getAttribute("loginUser");
		int  memberidx = memberVO.getMemberidx();
		FoodVO foodVO = new FoodVO();
		
		foodVO.setMemberidx(memberidx);
		
		List<FoodVO> li = service.selectCal(foodVO);
	    Map<String, Double> totals = calculateTotals(li);
	
	    model.addAttribute("li", li);
	    model.addAttribute("totalenerc", totals.get("totalenerc"));
	    model.addAttribute("totalchocdf", totals.get("totalchocdf"));
	    model.addAttribute("totalprot", totals.get("totalprot"));
	    model.addAttribute("totalfatce", totals.get("totalfatce"));
	    model.addAttribute("totalsugar", totals.get("totalsugar"));
	    if (!li.isEmpty()) {
	       model.addAttribute("memberidx", li.get(0).getMemberidx());
	    }
		return "/food/foodEdit.html";
		
	}
	@GetMapping("/deletecalIdx.do")
	String deletecalIdx(FoodVO vo){
	
	
	      service.deletecalIdx(vo);
	
	      return "redirect:/foodCalEdit.do";
	}
	@GetMapping("/deletecalAll.do")
	String deletecalAll(FoodVO vo,@RequestParam(name = "memberidx") int memberidx){
		vo.setMemberidx(memberidx);
		service.deletecalAll(vo);
	
	    return "redirect:/foodCalEdit.do";
	}
	@GetMapping("/charts.do")
	String charts(Model model,HttpSession session) {
		
		MemberVO memberVO=  (MemberVO)session.getAttribute("loginUser");
		int  memberidx = memberVO.getMemberidx();
		FoodVO foodVO = new FoodVO();
		foodVO.setMemberidx(memberidx);
		
		List<FoodVO> li = service.selectCal(foodVO);

	    Map<String, Double> totals = calculateTotals(li);
	    model.addAttribute("li", li);
	    model.addAttribute("totalenerc", totals.get("totalenerc"));
	    model.addAttribute("totalchocdf", totals.get("totalchocdf"));
	    model.addAttribute("totalprot", totals.get("totalprot"));
	    model.addAttribute("totalfatce", totals.get("totalfatce"));
	    model.addAttribute("totalsugar", totals.get("totalsugar"));
		
		return "/food/charts.html"; 
		
	}
	
	
	@PostMapping("/updateAmount.do")
	@ResponseBody
	public ResponseEntity<?> updateAmount(@RequestParam("calidx") int calidx,
						@RequestParam("amount")int amount,HttpSession session){
	    try {
	        // 해당 calidx의 음식 정보를 가져와서 amount를 업데이트
	        FoodVO food = service.getFoodByCalIdx(calidx);
	        food.setAmount(amount);
	        service.updateFoodAmount(food);
	        
	        // 업데이트된 총합을 계산
	        MemberVO memberVO=  (MemberVO)session.getAttribute("loginUser");
			int  memberidx = memberVO.getMemberidx();
			FoodVO foodVO = new FoodVO();
			foodVO.setMemberidx(memberidx);
	        List<FoodVO> updatedList = service.selectCal(foodVO);
	        Map<String, Double> totals = calculateTotals(updatedList);

	        // 업데이트된 총합을 클라이언트에게 전송
	        return ResponseEntity.ok(totals);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	//계산되는부분 totals로 전체 넘겨줌
	private Map<String, Double> calculateTotals(List<FoodVO> foodList) {
	    Map<String, Double> totals = new HashMap<>();

	    double totalenerc = 0.0;
	    double totalchocdf = 0.0;
	    double totalprot = 0.0;
	    double totalfatce = 0.0;
	    double totalsugar = 0.0;

	    for (FoodVO m : foodList) {
	        totalenerc += m.getEnerc() != null ? m.getEnerc() * m.getAmount() : 0.0;
	        totalchocdf += m.getChocdf() != null ? m.getChocdf() * m.getAmount() : 0.0;
	        totalprot += m.getProt() != null ? m.getProt() * m.getAmount() : 0.0;
	        totalfatce += m.getFatce() != null ? m.getFatce() * m.getAmount() : 0.0;
	        totalsugar += m.getSugar() != null ? m.getSugar() * m.getAmount() : 0.0;
	    }
	    totalenerc = Math.round(totalenerc * 100.0) / 100.0;
	    totalchocdf = Math.round(totalchocdf * 100.0) / 100.0;
	    totalprot = Math.round(totalprot * 100.0) / 100.0;
	    totalfatce = Math.round(totalfatce * 100.0) / 100.0;
	    totalsugar = Math.round(totalsugar * 100.0) / 100.0;
	    
	    totals.put("totalenerc", totalenerc);
	    totals.put("totalchocdf", totalchocdf);
	    totals.put("totalprot", totalprot);
	    totals.put("totalfatce", totalfatce);
	    totals.put("totalsugar", totalsugar);

	    return totals;
	}
	
}
	

