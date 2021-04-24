package com.hanul.ent.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.ent.dao.EntDAO;
import com.hanul.ent.dto.EntDTO;

public class EntMenuSelect implements EntCommand {
	@Override
	public void execute(Model model) {
		EntDAO dao = new EntDAO();
		
		ArrayList<EntDTO> list = dao.EntMenuSelect(model);
		
		model.addAttribute("list", list);
	}
}		