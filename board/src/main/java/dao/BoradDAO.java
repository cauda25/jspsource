package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDTO;

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

	public List<BoardDTO> getList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = getConnection();
			String spl = "select bno,name,title,readcnt,regdate,re_lev from board  order by RE_REF DESC, RE_SEQ ASC";
			pstmt = con.prepareStatement(spl);
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
	
}
