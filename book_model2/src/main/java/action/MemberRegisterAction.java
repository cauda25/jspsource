package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDTO;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;
import service.MemberService;
import service.MemberServiceImpl;

@AllArgsConstructor
public class MemberRegisterAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 가져오기
		MemberDTO insertDto = new MemberDTO();
		insertDto.setUserid(request.getParameter("userid"));
		insertDto.setName(request.getParameter("name"));
		insertDto.setPassword(request.getParameter("password"));
		
		// 2. service 호출
		MemberService service = new MemberServiceImpl();
		boolean insertRow = service.join(insertDto);
		
		if(!insertRow){
			path = "/register.do";
		}
		
		return new ActionForward(path, true);
	}

}
