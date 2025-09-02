package dev.coma.study.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import dev.coma.study.board.BoardFileDTO;
import dev.coma.study.board.BoardService;
import dev.coma.study.board.BoardVO;
import dev.coma.study.common.file.FileManager;
import dev.coma.study.common.page.Pager;

@Service
@Transactional
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
		Long totalCount = noticeDAO.selectTotalCount(pager);
		
		pager.initPage(totalCount);
		return noticeDAO.selectBoardList(pager);
	}

	@Override
	public BoardVO selectBoardDetail(BoardVO boardVO) throws Exception {
		return noticeDAO.selectBoardDetail(boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO, MultipartFile[] boardAttaches) throws Exception {
		int result = noticeDAO.insertBoard(boardVO);
		
		if (boardAttaches == null) return result;
		for (MultipartFile boardAttach : boardAttaches) {
			if (boardAttach == null || boardAttach.isEmpty()) continue;
			
			// File을 HDD에 저장
			String fileName = fileManager.fileSave(upload + board, boardAttach);
			
			// File 저장 정보를 DB에 저장
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setOriginName(boardAttach.getOriginalFilename());
			boardFileDTO.setSavedName(fileName);
			boardFileDTO.setBoardNum(boardVO.getBoardNum());
			noticeDAO.insertBoardAttach(boardFileDTO);
		}
		
		return result;
	}

	@Override
	public int updateBoard(BoardVO boardVO, MultipartFile[] boardAttaches) throws Exception {
		int result = noticeDAO.updateBoard(boardVO);
		
		if (boardAttaches == null) return result;
		for (MultipartFile boardAttach : boardAttaches) {
			if (boardAttach == null || boardAttach.isEmpty()) continue;
			
			String fileName = fileManager.fileSave(upload + board, boardAttach);
			
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setBoardNum(boardVO.getBoardNum());
			boardFileDTO.setOriginName(boardAttach.getOriginalFilename());
			boardFileDTO.setSavedName(fileName);
			noticeDAO.insertBoardAttach(boardFileDTO);
		}
		
		return result;
	}

	@Override
	public int deleteBoard(Long boardNum) throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardNum(boardNum);
		
		boardVO = noticeDAO.selectBoardDetail(boardVO);
		
		for (BoardFileDTO boardFileDTO : boardVO.getBoardFileDTOs()) {
			fileManager.fileDelete(upload + board, boardFileDTO.getSavedName());
		}
		
		int result = noticeDAO.deleteBoardAttach(boardNum);
		result = noticeDAO.deleteBoard(boardNum);
		
		return result;
	}

	@Override
	public int deleteBoardFile(BoardFileDTO boardFileDTO) throws Exception {
		boardFileDTO = noticeDAO.selectBoardAttach(boardFileDTO);
		boolean success = fileManager.fileDelete(upload + board, boardFileDTO.getSavedName());
		
		int result = noticeDAO.deleteBoardAttachOne(boardFileDTO.getFileNum());
		
		return result;
	}

	@Override
	public BoardFileDTO selectBoardAttach(BoardFileDTO boardFileDTO) throws Exception {
		return noticeDAO.selectBoardAttach(boardFileDTO);
	}

	@Override
	public String saveBoardFile(MultipartFile boardFile) throws Exception {
		if (boardFile == null || boardFile.isEmpty()) return null;
		
		String fileName = fileManager.fileSave(upload + board, boardFile);
		
		return "/files/" + board + "/" + fileName;
	}

	public boolean unsaveBoardFile(String fileName) throws Exception {
		fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
		
		return fileManager.fileDelete(upload + board, fileName);
	}

}
