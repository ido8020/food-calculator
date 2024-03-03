package com.rubypaper.money;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyServiceImpl implements MoneyService{
	@Autowired
	private MoneyDao dao;
	@Override
	public List<MoneyVO> MoneyList(MoneyVO vo) {
		// TODO Auto-generated method stub
		return dao.MoneyList(vo);
	}
	
}
