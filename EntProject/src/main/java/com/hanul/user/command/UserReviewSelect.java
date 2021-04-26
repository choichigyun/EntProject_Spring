package com.hanul.user.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.user.dao.UserDAO;
import com.hanul.user.dto.UserReviewDTO;

public class UserReviewSelect implements UserCommand {
	@Override
	public void execute(Model model) {
		UserDAO dao = new UserDAO();
		UserReviewDTO dto = (UserReviewDTO)model.asMap().get("dto");
		
		
		ArrayList<UserReviewDTO> list = dao.userReviewSelect(dto);
		model.addAttribute("list", list);
	}
}
