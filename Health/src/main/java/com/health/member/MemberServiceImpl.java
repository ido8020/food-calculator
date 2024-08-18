package com.health.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao dao;

	

	@Override
	public void memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		if (isIdExists(vo.getId())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }
		dao.memberInsert(vo);
	}
	

	@Override
	public List<MemberVO> memberList() {
		// TODO Auto-generated method stub
		return dao.memberList();
	}



	@Override
	public MemberVO loginOK(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.loginOK(vo);
	}



	@Override
	public MemberVO memberListOne(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.memberListOne(vo);
	}



	@Override
	public void memberUpdate(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.memberUpdate(vo);
	}


	@Override
	public boolean isIdExists(String id) {
		// TODO Auto-generated method stub
		return dao.selectMemberById(id) !=null;
	}
}
