package com.hanul.ent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ReviewController {
	
	
	
	
	//��縮�� ��ȸ
		@RequestMapping("/reviewList")
		public String reviewList(Model model) {
			System.out.println("reviewList()");
			
			
		
			
			/*
			 * command = new EntListSelect(); 
			 * command.execute(model); 
			 */
			
			return "reviewList";
		}

}
