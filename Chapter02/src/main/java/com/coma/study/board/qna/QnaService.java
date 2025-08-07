package com.coma.study.board.qna;

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
public class QnaService implements BoardService {
	@Autowired
	private QnaDAO qnaDAO;
	@Autowired
	private FileManager fileManager;
	@Value("${app.upload}")
	private String upload;
	@Value("${board.qna}")
	private String board;

	@Override
	public List<BoardVO> selectBoardList(Pager pager) throws Exception {
		Long totalCount = qnaDAO.selectTotalCount(pager);
		
		pager.initPage(totalCount);
		return qnaDAO.selectBoardList(pager);
	}

	@Override
	public BoardVO selectBoardDetail(BoardVO boardVO) throws Exception {
		return qnaDAO.selectBoardDetail(boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO, MultipartFile[] boardAttaches) throws Exception {
		int result = qnaDAO.insertBoard(boardVO);
		result = qnaDAO.updateRef(boardVO);
		
		for (MultipartFile boardAttach : boardAttaches) {
			if (boardAttach == null || boardAttach.isEmpty()) continue;
			
			String fileName = fileManager.fileSave(upload + board, boardAttach);
			
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setOriginName(boardAttach.getOriginalFilename());
			boardFileDTO.setSavedName(fileName);
			boardFileDTO.setBoardNum(boardVO.getBoardNum());
			qnaDAO.insertBoardAttach(boardFileDTO);
		}
		
		return result;
	}
	
	public int insertReply(QnaDTO qnaDTO) throws Exception {
		QnaDTO parent = (QnaDTO) qnaDAO.selectBoardDetail(qnaDTO);
		qnaDTO.setBoardRef(parent.getBoardRef());
		qnaDTO.setBoardStep(parent.getBoardStep() + 1);
		qnaDTO.setBoardDepth(parent.getBoardDepth() + 1);
		
		int result = qnaDAO.updateReplyStep(parent);
		result = qnaDAO.insertBoard(qnaDTO);
		
		return result;
	}

	@Override
	public int updateBoard(BoardVO boardVO) throws Exception {
		return 0;
	}

	@Override
	public int deleteBoard(Long boardNum) throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardNum(boardNum);
		
		boardVO = qnaDAO.selectBoardDetail(boardVO);
		
		for (BoardFileDTO boardFileDTO : boardVO.getBoardFileDTOs()) {
			fileManager.fileDelete(upload + board, boardFileDTO.getSavedName());
		}
		
		int result = qnaDAO.deleteBoardAttach(boardNum);
		result = qnaDAO.deleteBoard(boardNum);
		
		return result;
	}

}
