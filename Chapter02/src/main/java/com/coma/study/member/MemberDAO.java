package com.coma.study.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	int insertMember(MemberDTO memberDTO) throws Exception;
	int insertMemberProfile(MemberProfileDTO memberProfileDTO) throws Exception;
}
