package com.hanul.ent.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.hanul.ent.command.UserCommand;
import com.hanul.ent.command.UserReviewInsert;
import com.hanul.ent.command.UserReviewSelect;
import com.hanul.ent.dto.UserReviewDTO;

@Controller
public class UserController {
	
	UserCommand command;
	
	@RequestMapping("/userReviewSelect")
	public String userReviewSelect(HttpServletRequest req, Model model) {
		System.out.println("userReviewSelect");
		String user_nick = (String)req.getParameter("user_nick");
		String ent_id = (String)req.getParameter("ent_id");
		UserReviewDTO dto = new UserReviewDTO();
		dto.setEnt_id(ent_id);
		dto.setUsers_nick(user_nick);
		System.out.println("ent_id : " + ent_id + "\nuser_nick : " + user_nick);
		model.addAttribute("dto", dto);
		command = new UserReviewSelect();
		command.execute(model);
		return "userReview";
	}
	
	@RequestMapping("/userReviewInsert")
	public String userReviewInsert(HttpServletRequest req, Model model, HttpSession session) {
		System.out.println("userReviewInsert");
		String users_nick = (String) req.getParameter("users_nick");
		String ent_id = (String) req.getParameter("ent_id");
		String reviewTitle = (String) req.getParameter("user_reviewTitle");
		String review = (String) req.getParameter("user_review");
		String dbImgPath = (String) req.getParameter("dbImgPath");
		String resources = session.getServletContext().getRealPath("resources");
		String fileName = "";
		System.out.println("resources : " + resources);
		System.out.println("dbImgPath : " + dbImgPath);
		//파일저장 부분
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = multi.getFile("image");
			
		if(file != null) {
			fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			System.out.println(fileName);
			dbImgPath = dbImgPath.substring(0, dbImgPath.length()-file.getOriginalFilename().length()) + fileName;
			
			// 디렉토리 존재하지 않으면 생성
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/");
				
				System.out.println( fileName + " : " + realImgPath);
				System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// 이미지파일 저장
					file.transferTo(new File(realImgPath, fileName));										
				} catch (Exception e) {
					e.printStackTrace();
				} 
									
			}else{
				// 이미지파일 실패시
				fileName = "FileFail.jpg";
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
			}			
			
		}
				
		System.out.println("dbImgPath : " + dbImgPath);
		UserReviewDTO dto = new UserReviewDTO();
		dto.setUser_review(review);
		dto.setUser_reviewTitle(reviewTitle);
		dto.setUsers_nick(users_nick);
		dto.setEnt_id(ent_id);
		dto.setDbImgPath(dbImgPath);
		model.addAttribute("dto", dto);
		command = new UserReviewInsert();
		command.execute(model);
		System.out.println("users_nick : " + users_nick + "\nent_id : " + ent_id);
		System.out.println("reviewTitle : " + reviewTitle + "\nreview : " + review);
		return "";
	}
	
	//폴더 만들기
	public void makeDir(HttpServletRequest req){
		File f = new File(req.getSession().getServletContext()
				.getRealPath("/resources"));
		if(!f.isDirectory()){
		f.mkdir();
		}	
	}
	
}

