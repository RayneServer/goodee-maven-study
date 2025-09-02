package dev.coma.study.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import dev.coma.study.product.ProductDTO;

@Mapper
public interface MemberDAO {
	MemberDTO selectMember(MemberDTO memberDTO) throws Exception;
	int insertMember(MemberDTO memberDTO) throws Exception;
	int insertMemberProfile(MemberProfileDTO memberProfileDTO) throws Exception;
	int insertMemberRole(Map<String, Object> map) throws Exception;
	int insertCart(Map<String, Object> map) throws Exception;
	List<ProductDTO> selectCartList(MemberDTO memberDTO) throws Exception;
	int deleteCartList(Map<String, Object> Map) throws Exception;
	int updateMember(MemberDTO memberDTO) throws Exception;
	int updatePassword(MemberDTO memberDTO) throws Exception;
}
