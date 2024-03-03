package com.rubypaper.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao dao;

	@Override
	public List<MemberVO> MemberList(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.MemberList(vo);
	}

	@Override
	public int custno() {
		// TODO Auto-generated method stub
		return dao.custno();
	}

	@Override
	public void insert(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public MemberVO edit(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.edit(vo);
	}

	@Override
	public void update(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}
	
}
