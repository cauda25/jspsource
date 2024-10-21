package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardServiceImpl;
import service.BoardService;

@AllArgsConstructor

public class BoardListAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardService service = new BoardServiceImpl();
		List<BoardDTO> list = service.listAll();
		
		// list => board 테이브 내용
		// list.jsp 에 가서 화면에 출력
		request.setAttribute("list", list);
		
		return new ActionForward(path, false);
	}

}