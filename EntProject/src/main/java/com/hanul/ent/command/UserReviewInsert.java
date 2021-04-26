package com.hanul.ent.command;

import org.springframework.ui.Model;

import com.hanul.ent.dao.UserDAO;
import com.hanul.ent.dto.UserReviewDTO;

public class UserReviewInsert implements UserCommand {
	@Override
	public void execute(Model model) {
		UserReviewDTO dto = (UserReviewDTO) model.asMap().get("dto");
		System.out.println("User : "+dto.getUser_reviewTitle());
		UserDAO dao = new UserDAO();
		dao.userReviewInsert(dto);
	}
}
