package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BookDTO;
import dto.ChangeDTO;
import dto.MemberDTO;

public class MemberDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

//	// 드라이버 로드
//	static {
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	public Connection getConnection(){
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
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

	public MemberDTO islogin(MemberDTO loginDto) {
		MemberDTO dto = null;
		try {
			con = getConnection();
			String sql = "SELECT * FROM MEMBERTBL m WHERE USERID=? AND PASSWORD=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginDto.getUserid());
			pstmt.setString(2, loginDto.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		return dto;
	}
	public boolean dupId(String userid) {
		try {
			con = getConnection();
			String sql = "SELECT * FROM MEMBERTBL m WHERE USERID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {// id 있음 
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		return true; //중복 아이디 없음
	}
	public int insert(MemberDTO insertDto) {
		int insertRow = 0;
		try {
			con = getConnection();
			String sql ="INSERT INTO MEMBERTBL(userid,name,password) values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, insertDto.getUserid());
			pstmt.setString(2, insertDto.getName());
			pstmt.setString(3, insertDto.getPassword());
			
			insertRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,pstmt);
		}
		return insertRow;
	}
	
	public int update(ChangeDTO changeDto) {
		int updateRow = 0;
		try {
			con = getConnection();
			String sql ="UPDATE MEMBERTBL SET PASSWORD = ? WHERE userid= ? AND password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, changeDto.getChangePassword());
			pstmt.setString(2, changeDto.getUserid());
			pstmt.setString(3, changeDto.getCurrentPassword());
			
			updateRow = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(con,pstmt);
		}
		return updateRow;
	}
	
}











