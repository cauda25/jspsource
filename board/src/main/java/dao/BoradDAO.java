package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDTO;
import dto.PageDTO;
import dto.SearchDTO;

public class BoradDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 드라이버 로드
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##java";
		String password = "12345";

		return DriverManager.getConnection(url, user, password);
	}

	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BoardDTO> getList(SearchDTO sDto) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		// 시작번호
		int start = sDto.getPage() * sDto.getAmount();
		// 끝번호
		int end = (sDto.getPage()-1) * sDto.getAmount();
		
		try {
			con = getConnection();
			String sql = "select bno,name,title,readcnt,regdate,re_lev ";
			sql += "FROM (SELECT rownum rnum,bno,name,title,readcnt,regdate,re_lev ";
			sql += "FROM (SELECT bno,name,title,readcnt,regdate,re_lev from board ";
			
			
			if(!sDto.getCriteria().isBlank()) {
				sql += "where "+sDto.getCriteria()+ " LIKE ? order by RE_REF DESC, RE_SEQ ASC) ";
				sql += "WHERE rownum <= ?) ";
				sql += "WHERE rnum > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+sDto.getKeyword()+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}else {
				sql += " order by RE_REF DESC, RE_SEQ ASC) ";
				sql += "WHERE rownum <= ?) ";
				sql += "WHERE rnum > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}
//			String spl = "select bno,name,title,readcnt,regdate,re_lev from board where TITLE || CONTENT || NAME LIKE ? order by RE_REF DESC, RE_SEQ ASC";
//			pstmt = con.prepareStatement(spl);
//			pstmt.setString(1, "%"+sDto.getKeyword()+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setReLev(rs.getInt("re_lev"));

				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}

	public BoardDTO read(int bno) {
		BoardDTO dto = null;
		try {
			con = getConnection();
			String sql = "select * from board where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFileF(rs.getString("file_f"));
				
				// 댓글에 필요한 정보 담기
				dto.setReRef(rs.getInt("re_ref"));
				dto.setReLev(rs.getInt("re_lev"));
				dto.setReSeq(rs.getInt("re_seq"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return dto;
	}

	public int update(BoardDTO updateDto) {
		int updateRow = 0;
		try {
			con = getConnection();
			String sql = "UPDATE BOARD SET title=? , content=? WHERE bno=? AND password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateDto.getTitle());
			pstmt.setString(2, updateDto.getContent());
			pstmt.setInt(3, updateDto.getBno());
			pstmt.setString(4, updateDto.getPassword());

			updateRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return updateRow;
	}

	public int delete(BoardDTO deleteDto) {
		int deleteRow = 0;
		try {
			con = getConnection();
			String sql = "delete from board where bno = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deleteDto.getBno());
			pstmt.setString(2, deleteDto.getPassword());
			deleteRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return deleteRow;
	}

	public int create(BoardDTO insertDto) {
		int insertRow=0;
		int bno=0;
		try {
			con = getConnection();
//			String sql = "select board_seq.naxtval from dual";
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				bno =rs.getInt(1);
//			}
//			sql = "insert into board(bno,name,password,title,content,re_ref,re_lev,re_seq) values(?,?,?,?,?,?,0,0)";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, bno);
//			pstmt.setString(1, insertDto.getName());
//			pstmt.setString(2, insertDto.getPassword());
//			pstmt.setString(3, insertDto.getTitle());
//			pstmt.setString(4, insertDto.getContent());
//			pstmt.setInt(6, bno);
			
			String sql = "insert into board(bno,name,password,title,content,file_f,re_ref,re_lev,re_seq) values(board_seq.nextval,?,?,?,?,?,board_seq.currval,0,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, insertDto.getName());
			pstmt.setString(2, insertDto.getPassword());
			pstmt.setString(3, insertDto.getTitle());
			pstmt.setString(4, insertDto.getContent());
			pstmt.setString(5, insertDto.getFileF());
			
			insertRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return insertRow;
	}
	
	public int updateRead(int bno) {
		int updateReadRow=0;
		try {
			con = getConnection();
			String sql = "UPDATE BOARD SET READCNT = READCNT +1 WHERE bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			updateReadRow = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(con,pstmt);
		}
		return updateReadRow;
	}
	
	public int replyInsert(BoardDTO replyDto) {
		int insertRow=0;
		
		// 부모글 정보
		int bno= replyDto.getBno();
		int reRef = replyDto.getReRef();
		int reLev = replyDto.getReLev();
		int reSeq = replyDto.getReSeq();
		try {
			con = getConnection();
			String sql = "UPDATE BOARD SET RE_SEQ = RE_SEQ +1 WHERE RE_REF=? AND RE_SEQ > ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reRef);
			pstmt.setInt(2, reSeq);
			
			pstmt.executeUpdate();
			
			sql = "insert into board(bno,name,password,title,content,re_ref,re_lev,re_seq) values(board_seq.nextval,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, replyDto.getName());
			pstmt.setString(2, replyDto.getPassword());
			pstmt.setString(3, replyDto.getTitle());
			pstmt.setString(4, replyDto.getContent());
			pstmt.setInt(5, reRef);
			pstmt.setInt(6, reLev + 1);
			pstmt.setInt(7, reSeq + 1);
			
			insertRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return insertRow;
	}
	
	// 전체 게시물 개수 리턴 메소드
	public int getTogalRows(SearchDTO sDto) {
		int total = 0;
		try {
			con = getConnection();
			if(!sDto.getCriteria().isBlank()) {
				String sql = "select count(*) from board WHERE " +sDto.getCriteria()+" like ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+sDto.getKeyword()+"%");
			} else {
				String sql = "select count(*) from board";
				pstmt = con.prepareStatement(sql);				
			}
			rs=pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,pstmt,rs);
		}
		return total;
	}
	
}
