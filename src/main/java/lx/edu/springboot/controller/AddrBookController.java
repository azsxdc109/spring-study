package lx.edu.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lx.edu.springboot.dao.AddrBookDAO;
import lx.edu.springboot.vo.AddrBookVO;


@Controller
public class AddrBookController {
	
	@Autowired
	ApplicationContext context; // 이게 잇으면 생성된 빈즈들의 머시기..
	// Autowired는 이러한 타입의 빈이 있으면 연결시켜주는것
	
	@Autowired
	AddrBookDAO dao;
	
	@RequestMapping("/insert.do")
	public String insert(AddrBookVO vo) throws Exception {
		//1. 클라이언트(웹브라우저)가 보낸 데이터들을 추출하는 작업 -> 필드로 들어오는 것을 파싱해서 가져온다.
		/*
		 * 원래는 이렇게 해야햇자나 -> request에서 데이터 객체를 꺼내와서 vo에 맞춰야한다?
		 * String abName = req.getParameter("abName"); AddrBookVO vo = new AddrBookVO();
		 * vo.abName = abName;
		 */
		System.out.println(vo); // 알아서 객체를 만들어준다!?!!!!???, vo의 객체변수 이름과 같아서..? vo가..?
		//2. dao의 insertDB메서드에 vo를 넣어줘야해
		dao.insertDB(vo);
		return "redirect:addrbook_list.do"; // jsp file name을 문자열로 출력
	}
	
	@RequestMapping("/addrbook_form.do")
	public String form() {
		return "addrbook_form"; // jsp file name을 문자열로 출력
	}
	
	
	
//	 @RequestMapping("addrbook_list.do") 
//	 public String list(HttpServletRequest req) throws Exception { // 매개변수를..? // 여기서 해야하는 것... -> dao 활용하여 // 목록이 화면을통해 출력되기를 바라고 있음 List<AddrBookVO> list = dao.getDBList(); // 여기서 해야할 것 :가져온(DAO가 리턴한) list를 request에 넣어야한다. // list를 request에 넣는다.
//		 List<AddrBookVO> list = dao.getDBList();
//		 req.setAttribute("data", list); 
//		 return "addrbook_list"; 
//	
//	 }
//	 
	
	  // 로그인하면 머시기 하게
	  
	 @RequestMapping("addrbook_list.do") 
	 public String list(HttpSession session,HttpServletRequest req) throws Exception { 
		
		 // if문을 여기서 쓰면 비즈니스 로직을 만들기 힘들다?
//	  if(session.getAttribute("userId")==null) { 
//		  return "login"; 
//	  }
	 
		 List<AddrBookVO> list = dao.getDBList();
		 req.setAttribute("data", list);
		 
	  return "addrbook_list";
	  
	  }
	
//	// 위에 것을 리턴값을 ModelAndView로 리턴하게
//	/*
//	 * @RequestMapping("addrbook_list.do") // 필요하다면 매개변수를 넣어주면 돼 public ModelAndView
//	 * list(HttpSession session) throws Exception { ModelAndView result = new
//	 * ModelAndView(); List<AddrBookVO> list = dao.getDBList(); // list를 request에
//	 * 넣는다. result.addObject("data",list); // 위의 setAttribute와 동일하다고 보면 됨
//	 * result.setViewName("addrbook_list"); // 문자열 리턴 return result; }
//	 */
//	
	// 수정하는 메서드 진짜
	@RequestMapping("/addrbook_modify.do")
	public String modify(AddrBookVO vo) throws Exception {
		System.out.println(vo);
		dao.updateDB(vo);
		return "redirect:addrbook_list.do"; // 다시 목록으로 돌아가야함
	}
	
	// 일단 해당아이디의 정보가 폼에 떴으면 좋겠음 // 모델뷰로 하지말고 원래 하던, request 활용해서 할까
	@RequestMapping("/addrbook_edit_form.do")
	public ModelAndView modifyForm(@RequestParam("abId") int abId) throws Exception {
		ModelAndView result = new ModelAndView();
		AddrBookVO data = dao.getOne(abId);
		System.out.println(data);
		
		result.addObject("data",data);
		result.setViewName("addrbook_edit_form");
		return result; 
	}
}
