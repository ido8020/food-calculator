package com.health.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl  implements FoodService{
	@Autowired
	private FoodDao dao;
	@Override
	public void calInsert(FoodVO vo) {
		// TODO Auto-generated method stub
		dao.calInsert(vo);
	}

	@Override
	public List<FoodVO> selectAll(FoodVO vo) {
		// TODO Auto-generated method stub
		return dao.selectAll(vo);
	}

	@Override
	public int totalCount(FoodVO vo) {
		// TODO Auto-generated method stub
		return dao.totalCount(vo);
	}

	@Override
	public FoodVO selectOne(String foodcd) {
		// TODO Auto-generated method stub
		return dao.selectOne(foodcd);
	}

	@Override
	public List<FoodVO> selectCal(FoodVO vo) {
		// TODO Auto-generated method stub
		return dao.selectCal(vo);
	}

	@Override
	public void deletecalIdx(FoodVO vo) {
		// TODO Auto-generated method stub
		dao.deletecalIdx(vo);
	}

	@Override
	public FoodVO getFoodByCalIdx(int calidx) {
		// TODO Auto-generated method stub
		return dao.getFoodByCalIdx(calidx);
	}

	@Override
	public void updateFoodAmount(FoodVO vo) {
		// TODO Auto-generated method stub
		dao.updateFoodAmount(vo);
	}

	@Override
	public void deletecalAll(FoodVO vo) {
		// TODO Auto-generated method stub
		dao.deletecalAll(vo);
	}



}
