package com.health.food;

import java.util.List;

public interface FoodService {
	//식단계산 입력
		void calInsert(FoodVO vo);
		//계산테이블에 음식 입력하는 용도
		FoodVO selectOne(String foodcd);
		//계산테이블 불러오기
		List<FoodVO> selectCal(FoodVO vo);

		void deletecalIdx(FoodVO vo);
		void deletecalAll(FoodVO vo);
		FoodVO getFoodByCalIdx(int calidx);
		
		void updateFoodAmount(FoodVO vo);
		//음식 리스트관련
		List<FoodVO> selectAll(FoodVO vo);
		int totalCount(FoodVO vo);
		
}