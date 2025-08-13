package com.coma.study.common.filter;

import java.io.IOException;

import com.coma.study.member.MemberDTO;
import com.coma.study.member.RoleDTO;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AdminCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		boolean flag = false;
		
		if (loginMember != null) {
			for (RoleDTO role : loginMember.getRoleDTOs()) {
				if (role.getRoleName().equals("ROLE_ADMIN")) {
					flag = !flag;
					break;
				}
			}
		}
		
		if (flag) chain.doFilter(request, response);
		else {
			request.setAttribute("resultMsg", "권한이 없습니다.");
			request.setAttribute("resultIcon", "warning");
			request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp").forward(request, response);
		}
	}
}
