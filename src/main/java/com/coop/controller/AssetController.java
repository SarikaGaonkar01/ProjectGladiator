package com.coop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.coop.dao.AssetDao;
import com.coop.model.Asset;
import com.coop.model.Registration;

@Controller
public class AssetController {
	
	@Autowired
	private AssetDao dbobj;
	
	@RequestMapping(value="/assetCheck",method=RequestMethod.POST)
	public ModelAndView checkAsset(HttpServletRequest request,HttpServletResponse response,@ModelAttribute Asset a,HttpSession session)
	{
		ModelAndView mv=null;
		
		
		System.out.println("404 test 1");
		Registration r=(Registration)session.getAttribute("user");
		a.setAccno(r.getAccno());
		
		System.out.println("404 test 2");
		int loan_id=(Integer)session.getAttribute("loanid");
		
		int seq=dbobj.saveAsset(a);
		a.setAsset_id(seq);
		System.out.println("Asset Sequence:"+seq);
		
		int status=dbobj.checkAsset(a,loan_id);
		
		if(status ==1)
		{
			mv=new ModelAndView("ValidatedLoan");
			int result=dbobj.approveLoan(loan_id,a.getAccno());
			System.out.println("Loan_request_status:"+result);
			session.setAttribute("loan_id", loan_id);
			
		}
			
		else
		{
			mv=new ModelAndView("cibil");
			int result=dbobj.rejectRequest(loan_id);
			System.out.println("result status:"+result);
		}
			
			
		
    return mv;
		
		
	}

}
