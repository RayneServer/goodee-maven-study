package dev.coma.study.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dev.coma.study.board.BoardFileDTO;
import dev.coma.study.board.BoardVO;
import dev.coma.study.common.page.Pager;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String name;
	
	@ModelAttribute("boardName")
	public String getBoardName() {
		return name;
	}
	
	@GetMapping("list")
	public String getQnaList(Pager pager, Model model) throws Exception {
		List<BoardVO> qnaList = qnaService.selectBoardList(pager);
		
		if (qnaList.size() > 0) {
			model.addAttribute("boardList", qnaList);
		}
		
		return "board/list";
	}
	
	@GetMapping("detail")
	public String getQnaDetail(QnaDTO qnaDTO, Model model) throws Exception {
		BoardVO qna = qnaService.selectBoardDetail(qnaDTO);
		
		if (qna != null) {
			model.addAttribute("board", qna);
		}
		
		return "board/detail";
	}
	
	@GetMapping("add")
	public String getQnaAdd() {
		return "board/add";
	}
	
	@PostMapping("add")
	public String postQnaAdd(QnaDTO qnaDTO, MultipartFile[] boardAttaches, Model model) throws Exception {
		int result = qnaService.insertBoard(qnaDTO, boardAttaches);
		
		model.addAttribute("resultMsg", "게시글 등록에 실패했습니다.");
		model.addAttribute("url", "list");
		
		if (result > 0) {
			model.addAttribute("resultMsg", "게시글 등록에 성공했습니다.");
		}
		
		return "commons/result";
	}
	
	@GetMapping("reply")
	public String getQnaReply(QnaDTO qnaDTO, Model model) throws Exception {
		model.addAttribute("board", qnaDTO);
		
		return "board/add";
	}
	
	@PostMapping("reply")
	public String postQnaReply(QnaDTO qnaDTO, MultipartFile[] boardAttaches, Model model) throws Exception {
		int result = qnaService.insertReply(qnaDTO, boardAttaches);
		
		model.addAttribute("resultMsg", "게시글 등록에 실패했습니다.");
		model.addAttribute("url", "list");
		
		if (result > 0) {
			model.addAttribute("resultMsg", "게시글 등록에 성공했습니다.");
		}
		
		return "commons/result";
	}
	
	@GetMapping("update")
	public String getQnaUpdate(QnaDTO qnaDTO, Model model) throws Exception {
		BoardVO boardVO = qnaService.selectBoardDetail(qnaDTO);
		
		model.addAttribute("board", boardVO);
		return "board/add";
	}
	
	@PostMapping("update")
	public String postQnaUpdate(QnaDTO qnaDTO, MultipartFile[] boardAttaches, Model model) throws Exception {
		int result = qnaService.updateBoard(qnaDTO, boardAttaches);
		
		model.addAttribute("resultMsg", "게시글 등록에 실패했습니다.");
		model.addAttribute("url", "list");
		
		if (result > 0) {
			model.addAttribute("resultMsg", "게시글 등록에 성공했습니다.");
		}
		
		return "commons/result";
	}
	
	@PostMapping("delete")
	public String postQnaDelete(Long boardNum, Model model) throws Exception {
		int result = qnaService.deleteBoard(boardNum);
		
		model.addAttribute("resultMsg", "게시글 삭제에 실패했습니다.");
		model.addAttribute("url", "list");
		
		if (result > 0) {
			model.addAttribute("resultMsg", "게시글 삭제에 성공했습니다.");
		}
		
		return "commons/result";
	}
	
	@PostMapping("fileDelete")
	@ResponseBody
	public int postFileDelete(BoardFileDTO boardFileDTO, Model model) throws Exception {
		int result = qnaService.deleteBoardFile(boardFileDTO);

		return result;
	}
	
	@GetMapping("fileDownload")
	public String getFileDownload(BoardFileDTO boardFileDTO, Model model) throws Exception {
		boardFileDTO = qnaService.selectBoardAttach(boardFileDTO);
		model.addAttribute("boardFile", boardFileDTO);
		
		return "fileDownloadView";
	}
	
	@PostMapping("boardFile")
	@ResponseBody
	public String postBoardFile(MultipartFile boardFile) throws Exception {
		return qnaService.saveBoardFile(boardFile);
	}
	
	@PostMapping("boardFileDelete")
	@ResponseBody
	public boolean postBoardFileDelete(String fileName) throws Exception {
		return qnaService.unsaveBoardFile(fileName);
	}
}
