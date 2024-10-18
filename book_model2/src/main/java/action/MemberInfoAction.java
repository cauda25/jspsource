package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dto.ChangeDTO;
import dto.MemberDTO;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;
import service.MemberService;
import service.MemberServiceImpl;

@AllArgsConstructor
public class MemberInfoAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ChangeDTO changeDto = new ChangeDTO();
		changeDto.setChangePassword(request.getParameter("change_password"));
		changeDto.setUserid(request.getParameter("userid"));
		changeDto.setCurrentPassword(request.getParameter("current_password"));
		
		MemberService service = new MemberServiceImpl();
		boolean updateRow = service.changePassword(changeDto);

		if(updateRow){
			HttpSession session = request.getSession();
			session.invalidate(); 
		} else {
			path = "/member/info.jsp";
		}

		return new ActionForward(path, true);
	}

}
