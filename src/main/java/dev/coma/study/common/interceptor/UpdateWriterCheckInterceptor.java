package dev.coma.study.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import dev.coma.study.board.BoardVO;
import dev.coma.study.member.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateWriterCheckInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (request.getMethod().toUpperCase().equals("POST")) return;
		
		MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("loginMember");
		String userName = memberDTO.getMemberId();
		
		BoardVO boardVO = (BoardVO) modelAndView.getModel().get("board");
		String writerName = boardVO.getBoardWriter();
		
		if (!userName.equals(writerName)) {
			modelAndView.setViewName("commons/result");
			modelAndView.addObject("resultMsg", "작성자만 수정할 수 있습니다.");
			modelAndView.addObject("resultIcon", "warning");
		}
	}
}
