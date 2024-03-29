package com.health.food;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface FoodDao {
	//식단계산 입력
	void calInsert(FoodVO vo);
	//계산테이블에 음식 입력하는 용도
	FoodVO selectOne(String foodcd);
	//계산테이블 불러오기
	List<FoodVO> selectCal(FoodVO vo);
	//계산기에 삭제,수정
	void deletecalIdx(FoodVO vo);
	void deletecalAll(FoodVO vo);
	void updateFoodAmount(FoodVO vo);
	
	//음식 리스트관련
	List<FoodVO> selectAll(FoodVO vo);
	int totalCount(FoodVO vo);
	//계산기할때 amount 업데이트용
	FoodVO getFoodByCalIdx(int calidx);
	
}
