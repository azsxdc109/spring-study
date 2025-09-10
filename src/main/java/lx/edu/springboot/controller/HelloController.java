package lx.edu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import lx.edu.springboot.vo.AddrBookVO;
import lx.edu.springboot.dao.AddrBookDAO;

@Controller
public class HelloController {
	
	AddrBookDAO dao;
	
	@RequestMapping("/hello")
	public String hello(Model model) throws Exception {
		
		AddrBookVO vo = dao.getDB(24);
		vo.setAbName("hahaha");
		model.addAttribute("vo",vo);
		return "hello";
	}
}
