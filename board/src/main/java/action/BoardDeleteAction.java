package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardServiceImpl;
import service.BoardService;

@AllArgsConstructor

public class BoardDeleteAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardDTO dto = new BoardDTO();
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		dto.setPassword(request.getParameter("password"));
				
		BoardService service = new BoardServiceImpl();
		Boolean del = service.delete(dto);
		
		if(del) {
			//path += "?bno="+"";
		} else {
			path = "/modify.do?bno="+dto.getBno();
		}
		
		
		return new ActionForward(path, true);
	}

}
