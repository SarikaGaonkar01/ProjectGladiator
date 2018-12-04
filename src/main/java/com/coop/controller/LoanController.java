package com.coop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.coop.dao.LoanDao;
import com.coop.model.Loan;
import com.coop.model.Registration;

@Controller
public class LoanController {
	
	@Autowired
	private LoanDao db;

	
	@RequestMapping(value="/requestloan",method=RequestMethod.POST)
	public ModelAndView loanRequest(@ModelAttribute Loan l)
	{
		ModelAndView mv=new ModelAndView("Loan");
		return mv;
	}
	
	@RequestMapping(value="/checkCibil",method=RequestMethod.POST)
	public ModelAndView checkCibil(HttpServletRequest request,HttpServletResponse response,@ModelAttribute Loan l, HttpSession session)
	{
		System.out.println("loan test 1......");
		ModelAndView mv=null;
		
		//HttpSession session=request.getSession(false);
		Registration user=(Registration)session.getAttribute("user");
		
		System.out.println("Session values:"+user.getAccno());
		System.out.println("-----------------------------------------------------");
		
		
		
		l.setStatus(0);
		//System.out.println("loan test 2  status:"+l.getStatus());
		l.setB_id(user.getBdetail());
		l.setAcc_no(user.getAccno());
		
		//System.out.println("loan test 3.......");
		
		
		String time=null;
		double rate=0;
		time=l.getTime();
		System.out.println(time);
		
		if(time.equals("sta"))
		{
			rate=4.0;
			l.setRate(rate);
			System.out.println("rate=4.0....");
		}
		else if(time.equals("mta"))
		{
			rate=8.0;
			l.setRate(rate);
			System.out.println("rate=8.0...");
		}
		else
		{
			rate=8.8;
			l.setRate(rate);
			System.out.println("rate=8.8....");
		}
		
		//test
		//System.out.println(l.getStatus());
		//System.out.println(l.getB_id());
		//System.out.println(l.getAcc_no());
		//System.out.println(l.getRate());
		
		
		
		
			int sequence = db.save(l);
			l.setLoan_id(sequence);
		
		
		//System.out.println("received sequence:"+l.getLoan_id());
		
		
		//character check-----1
		int status=db.getCibil(user);
		
		if(status>0)
		{
			mv=new ModelAndView("Asset");
			session.setAttribute("loanid", sequence);
		}
		else
		{
			mv=new ModelAndView("LowCibil");
			int result=db.rejectRequest(l.getLoan_id());
			System.out.println("result:"+result);
		}
			
		
		System.out.println("loan test 7.....");
		
		return mv;
		
	}

	
	
	
		
		
}
	
	



