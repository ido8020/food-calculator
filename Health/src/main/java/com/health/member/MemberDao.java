package com.health.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
	void memberInsert(MemberVO vo);
	List<MemberVO> memberList();
	MemberVO loginOK(MemberVO vo);
	MemberVO memberListOne(MemberVO vo);
	void memberUpdate(MemberVO vo);
	// 아이디로 회원 조회
    MemberVO selectMemberById(String id);

}
