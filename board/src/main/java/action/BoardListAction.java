package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import dto.PageDTO;
import dto.SearchDTO;
import lombok.AllArgsConstructor;
import service.BoardServiceImpl;
import service.BoardService;

@AllArgsConstructor

public class BoardListAction implements Action {

	private String path;
	
	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 페이지 나누기
		int page = Integer.parseInt(request.getParameter("page"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		// 검색 추가
		String criteria = request.getParameter("criteria");
		String keyword = request.getParameter("keyword");
		SearchDTO sDto = new SearchDTO(criteria,keyword,page,amount);
		
		BoardService service = new BoardServiceImpl();
		List<BoardDTO> list = service.listAll(sDto);
		
		int total = service.getTogalRows();
		PageDTO pDto = new PageDTO(sDto, total);
		
		// list => board 테이브 내용
		// list.jsp 에 가서 화면에 출력
		request.setAttribute("list", list);
		request.setAttribute("sDto", sDto);
		request.setAttribute("pDto", pDto);
			
		
		return new ActionForward(path, false);
	}

}
