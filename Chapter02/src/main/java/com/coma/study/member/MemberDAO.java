package com.coma.study.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	MemberDTO selectMember(MemberDTO memberDTO) throws Exception;
	int insertMember(MemberDTO memberDTO) throws Exception;
	int insertMemberProfile(MemberProfileDTO memberProfileDTO) throws Exception;
	int insertMemberRole(Map<String, Object> map) throws Exception;
}
