package com.codingbox.core4.domain.web.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
	@RequestMapping("/response-view-v1")
	public ModelAndView responseViewV1() {
		ModelAndView mav = new ModelAndView("response/hello")
								.addObject("data", "hello");
		
		return mav;
		
	}
	
	@RequestMapping("/response-view-v2")
	public String responseViewV2(Model model) {
		model.addAttribute("data", "hello");
		
		return "response/hello";
	}
	
	/*
	 * String을 반환하는 경우 - view or HTTP 메세지
	 * - @ResponseBody 가 없으면 : response/hello 로 뷰 리졸버가 실행되어서 뷰를 찾고
	 * 렌더링
	 * - @ResponseBody가 있으면 
	 * : 뷰 리졸버를 실행하지 않고, HTTP 메세지 바디에 직접 response/hello 라는 
	 * 문자열을 반환한다.
	 */
	
	@ResponseBody
	@RequestMapping("/response-view-v3")
	public String responseViewV3(Model model) {
		model.addAttribute("data", "hello3");
		
		return "response/hello";
	}
}
