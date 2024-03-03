package com.rubypaper.guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestbookServiceImpl implements GuestbookService{
	@Autowired
	private GuestbookDao dao;

	@Override
	public void insert100(GuestbookVO vo) {
		// TODO Auto-generated method stub
		dao.insert100(vo);
	}

	@Override
	public List<GuestbookVO> GuestbookList(GuestbookVO vo) {
		// TODO Auto-generated method stub
		return dao.GuestbookList(vo);
	}

	@Override
	public int totalcount(GuestbookVO vo) {
		return dao.totalcount(vo);
	}

}
