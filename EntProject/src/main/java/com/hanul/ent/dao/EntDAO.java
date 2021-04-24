package com.hanul.ent.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.ent.dto.EntDTO;

public class EntDAO {

	private Connection conn;		//연결객체
	private PreparedStatement ps;	//전송객체
	private ResultSet rs;			//결과객체
	
	//DB접속
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
	
	//DB접속 해제
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
	
	//가게들 불러오기
	public ArrayList<EntDTO> EntListSelect() {
		conn = getConn();
		String sql = "select * from ent";
		EntDTO dto = new EntDTO();
		ArrayList<EntDTO> list = new ArrayList<EntDTO>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String ent_id = rs.getString("ent_id");
				String ent_nick = rs.getString("ent_nick");
				String ent_name = rs.getString("ent_name");
				String ent_lat = rs.getString("ent_lat");
				String ent_long = rs.getString("ent_long");
				String ent_proof = rs.getString("ent_proof");

				dto = new EntDTO(ent_id, ent_nick, ent_name, ent_lat, ent_long, ent_id, ent_id, ent_long, ent_proof);
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	//메뉴, 메뉴사진 불러오기
	public ArrayList<EntDTO> EntMenuSelect(Model model) {
		conn = getConn();
		//메뉴를 불러오고 그 메뉴의 사진도 같이 불러온다..
		String sql = "select m.ent_id, m.menu, f.fdpicture_path from menu m, fdpicture f where m.menu = f.menu and m.ent_id = ?";
		EntDTO dto = new EntDTO();
		ArrayList<EntDTO> list = new ArrayList<EntDTO>();
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)model.asMap().get("ent_id"));
			rs = ps.executeQuery();
			while (rs.next()) {
				String ent_id = rs.getString("ent_id");
				String ent_menu = rs.getString("menu");
				String ent_menu_picture = rs.getString("fdpicture_path");
				dto = new EntDTO(ent_id, ent_menu, ent_menu_picture);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


}
