package com.monstar.books.adevent.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monstar.books.adevent.sevice.AdEventDeleteService;
import com.monstar.books.adevent.sevice.AdEventDetailService;
import com.monstar.books.adevent.sevice.AdEventService;
import com.monstar.books.adevent.sevice.AdEventServiceList;

@Controller
public class AdEventController {

	@Autowired
	AdEventService service;

	@Autowired
	private SqlSession session;

	@RequestMapping("admin/event/event")
	public String event(HttpServletRequest request,Model model) {
		System.out.println("event");

		model.addAttribute("request", request);
//		model.addAttribute("searchVO", searchVO);

		service = new AdEventServiceList(session);
		service.execute(model);
		
		return "admin/event/event";
	}// event 종료
	@RequestMapping("admin/event/eventdetail")
	public String eventdetail(HttpServletRequest request,Model model) {
		System.out.println("eventdetail");

		model.addAttribute("request", request);

		service = new AdEventDetailService(session);
		service.execute(model);
		
		return "admin/event/eventdetail";
	}// event 종료
	@RequestMapping("admin/event/eventdelete")
	public String eventdelete(HttpServletRequest request,Model model) {
		System.out.println("eventdelete");

		model.addAttribute("request", request);

		service = new AdEventDeleteService(session);
		service.execute(model);
		
		return "redirect:/admin/event/event";
	}// event 종료

}

