package com.health.member;

import java.util.List;

public interface MemberService {
	void memberInsert(MemberVO vo);
	List<MemberVO> memberList();
	MemberVO loginOK(MemberVO vo);
	MemberVO memberListOne(MemberVO vo);
	void memberUpdate(MemberVO vo);
}
