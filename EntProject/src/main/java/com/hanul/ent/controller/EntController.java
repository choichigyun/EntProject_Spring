package com.hanul.ent.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.ent.command.EntCommand;
import com.hanul.ent.command.EntListSelect;
import com.hanul.ent.command.EntMenuSelect;

@Controller
public class EntController {
	
	EntCommand command;
	

	
	//메뉴, 메뉴사진 조회
	@RequestMapping(value="/entMenu", method = {RequestMethod.GET, RequestMethod.POST})
	public String entMenu(HttpServletRequest req, Model model) {
		System.out.println("entMenu");
		
		String ent_id = (String) req.getParameter("ent_id");
		model.addAttribute("ent_id", ent_id);
		command = new EntMenuSelect();
		command.execute(model);
		
		System.out.println(ent_id);
		return "entMenuList";
	}
	
	//모든가게 조회
	@RequestMapping("/entList")
	public String entList(Model model) {
		System.out.println("entList");
		
		command = new EntListSelect();
		command.execute(model);
		return "entListList";
	}
	
}
