package com.hanul.ent.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.hanul.ent.dto.UserReviewDTO;

public class UserDAO {
	private Connection conn;		//楷搬按眉
	private PreparedStatement ps;	//傈价按眉
	private ResultSet rs;			//搬苞按眉
	
	//DB立加
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "android";
		String password = "0000";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getConn() Exception!!!");
		}
		return conn;
	}//getConn()
	
	//DB立加 秦力
	public void dbClose() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dbClose() Exception!!!");
		}
	}//dbClose()
	
	//蜡历府轰 殿废
	public void userReviewInsert(UserReviewDTO dto) {
		conn = getConn();
		String sql = "insert into userreview(users_nick, ent_id, review, review_image_path) values(?,?,?,?)";
		try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, dto.getUsers_nick());
				ps.setString(2, dto.getEnt_id());
				ps.setString(3, dto.getUser_review());
				ps.setString(4, dto.getDbImgPath());
				ps.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<UserReviewDTO> userReviewSelect(UserReviewDTO dto) {
		conn = getConn();
		String sql = "select * from userreview u where ent_id = ? order by num desc ";
		ArrayList<UserReviewDTO> list = new ArrayList<UserReviewDTO>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getEnt_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				String users_nick = rs.getString("users_nick");
				String ent_id = rs.getString("ent_id");
				String review = rs.getString("review");
				String review_image = rs.getString("review_image_path");
				UserReviewDTO dtos = new UserReviewDTO(users_nick, review, ent_id, review_image);
				list.add(dtos);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

}
