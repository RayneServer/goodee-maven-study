package com.coma.study.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coma.study.board.BoardFileDTO;
import com.coma.study.board.BoardService;
import com.coma.study.board.BoardVO;
import com.coma.study.common.file.FileManager;
import com.coma.study.common.page.Pager;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private FileManager fileManager;
	@Value("${app.upload}")
	private String upload;
	@Value("${board.notice}")
	private String board;

	@Override
	public List<BoardVO> selectBoardList(Pager pager) throws Exception {
		Long totalCount = noticeDAO.selectTotalCount();
		
		pager.initPage(totalCount);
		return noticeDAO.selectBoardList(pager);
	}

	@Override
	public BoardVO selectBoardDetail(BoardVO boardVO) throws Exception {
		return noticeDAO.selectBoardDetail(boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO, MultipartFile boardAttach) throws Exception {
		int result = noticeDAO.insertBoard(boardVO);
		
		// File을 HDD에 저장
		String fileName = fileManager.fileSave(upload + board, boardAttach);
		
		// File 저장 정보를 DB에 저장
		BoardFileDTO boardFileDTO = new BoardFileDTO();
		boardFileDTO.setOriginName(boardAttach.getOriginalFilename());
		boardFileDTO.setSavedName(fileName);
		boardFileDTO.setBoardNum(boardVO.getBoardNum());
		noticeDAO.insertBoardAttach(boardFileDTO);
		
		return result;
	}

	@Override
	public int updateBoard(BoardVO boardVO) throws Exception {
		return noticeDAO.updateBoard(boardVO);
	}

	@Override
	public int deleteBoard(Long boardNum) throws Exception {
		return noticeDAO.deleteBoard(boardNum);
	}

}
