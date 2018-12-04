package com.coop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.coop.dao.JdbcDao;
import com.coop.model.AddPayee;
import com.coop.model.DisplayTransaction;
import com.coop.model.FlagStatus;
import com.coop.model.Login;
import com.coop.model.Registration;
import com.coop.model.TransferDetails;

@Controller
public class NewController {
	@Autowired
	private JdbcDao dbobj;
	@Autowired
	private JdbcDao dao;

	private String uname;
	private String accno;
	

	@RequestMapping(value="/logout")
	public ModelAndView logout()
	{
		return new ModelAndView("logout");
	}
	
	@RequestMapping(value="/forgotUsername",method=RequestMethod.POST)
	public ModelAndView forgotUsername()
	{
      return new  ModelAndView("askname");		
	}
	
	@RequestMapping(value="/forgotPassword",method=RequestMethod.POST)
	public ModelAndView forgotPassword()
	{
		return new ModelAndView("askinfo");
	}
	
	@RequestMapping(value="/updateUsername",method=RequestMethod.POST)
	public ModelAndView updateUsername(@ModelAttribute Registration r)
	{
		ModelAndView mv=null;
		int status=0;
		status=dbobj.updateUsername(r.getAccno(), r.getUname());
		if(status==1)
		  mv= new ModelAndView("LoginPage");
		else
		  mv=new ModelAndView("Norecord");
					
		return mv;
		
	}
	
	
	
	
	
	@RequestMapping(value="/updatepassword",method=RequestMethod.POST)
	public ModelAndView updatePassword(@ModelAttribute Registration r)
	{
		ModelAndView mv=null;
		int status=0;
		status=dbobj.updatePassword(r.getAccno(),r.getPassword());
		if(status==1)
		  mv= new ModelAndView("LoginPage");
		else
		  mv=new ModelAndView("Norecord");
		
		return mv;
	}
	
	@RequestMapping(value="/forgotsomething",method=RequestMethod.POST)
	public ModelAndView forgotSomething()
	{
		return new ModelAndView("forpass");
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ModelAndView weltotransfer() {
		return new ModelAndView("transfer");
	}

	@RequestMapping(value = "/returnHome", method = RequestMethod.POST)
	public ModelAndView home() {
		// returns to dashboard page(welcome)
		ModelAndView mv = new ModelAndView("WelcomePage");
		return mv;
	}

	// register controller
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Registration r) {

		// test case account no

		/*
		 * String bank="sbj"; if(r.getBdetail().equals(bank)) r.setAccno("sbj14622");
		 */

		System.out.println("test1....");
		System.out.println("check");
        
		System.out.println(r.getPassword());
		String str_acc = dbobj.savedata(r);
		r.setAccno(str_acc);
        System.out.println(r.getAccno());
		System.out.println("test2...");
		System.out.println("check");
		return new ModelAndView("LoginPage");
	}

	// login controller
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Login u,
			HttpSession session) {

		ModelAndView mv = null;

		// System.out.println("uname:"+u.getUname());
		uname = u.getUname();
		System.out.println(u.getPass());

		// int flag = dbobj.validate(u);
		Registration reg = dbobj.validateUser(u);

		// modified code
		if (null != reg) {
			System.out.println("logtest4");

			// session for current object
			session.setAttribute("user", reg);
			Registration user =(Registration)session.getAttribute("user");// Retrieved through session
			String accno=user.getAccno();
			
			FlagStatus regi = dao.validateBalance(accno);
			// modified code
			if (null != regi) {
				System.out.println("logtest4");
				// HttpSession session = request.getSession(true);
				int getbalance = dao.getbalancenow(accno);
				System.out.println(getbalance);
				System.out.println(regi.getGa_transfer_amount());
				int newbal = getbalance + Integer.parseInt(regi.getGa_transfer_amount());
				System.out.println(newbal);
				dao.updatenewbalance(accno, newbal);
				int transaction_no = Integer.parseInt(regi.getGa_transaction_no());
				dao.deleteflag(transaction_no);
				mv = new ModelAndView("WelcomePage");

				return mv;
			} else {
				System.out.println("logTest5");
				mv = new ModelAndView("WelcomePage");
			}

			// String no = reg.getAccno();
			// session.setAttribute("accno", no);

			

			return mv;
		} else {
			System.out.println("logTest4");
			return new ModelAndView("LoginPage");
		}

	}

	// performs payee operations like add, view and delete payee and to update
	@RequestMapping(value = "/payeeDash", method = RequestMethod.POST)
	public ModelAndView showpayeedash(HttpSession session) {
		ModelAndView mv = null;
		mv = new ModelAndView("payeeDashboard");
		return mv;
		/*
		Registration user =(Registration)session.getAttribute("user");// Retrieved through session
		String accno=user.getAccno();
		ModelAndView mv = null;
		FlagStatus reg = dao.validateBalance(accno);
		// modified code
		if (null != reg) {
			System.out.println("logtest4");
			// HttpSession session = request.getSession(true);
			int getbalance = dao.getbalancenow(accno);
			System.out.println(getbalance);
			System.out.println(reg.getGa_transfer_amount());
			int newbal = getbalance + Integer.parseInt(reg.getGa_transfer_amount());
			System.out.println(newbal);
			dao.updatenewbalance(accno, newbal);
			int transaction_no = Integer.parseInt(reg.getGa_transaction_no());
			dao.deleteflag(transaction_no);
			   //check

		} else {
			System.out.println("logTest5");
			return new ModelAndView("payeeDashboard");
		}*/

	}

	// Links to payeeForm.jsp
	@RequestMapping(value = "/addPayee", method = RequestMethod.POST)
	public ModelAndView showpayeeform() {
		return new ModelAndView("payeeForm");
	}
	// Links to payeeForm.jsp
		@RequestMapping(value = "/TransferLog", method = RequestMethod.POST)
		public ModelAndView transferlog(HttpSession session) {
			System.out.println("Prabhat is a dude");
			Registration user =(Registration)session.getAttribute("user");// Retrieved through session
			String accno = user.getAccno();
			System.out.println("Prabhat is not a dude");
			System.out.println(accno);
			List<DisplayTransaction> list = dao.getalltransaction(accno);
			return new ModelAndView("TransferLog", "list", list);
		}

	// Links to paymentDashboard.jsp
	@RequestMapping(value = "/makeTransfer", method = RequestMethod.POST)
	public ModelAndView makeTransfer() {
		return new ModelAndView("paymentDashboard");
	}

	// Links to paymentDashboard.jsp
	@RequestMapping(value = "/neftTransfer", method = RequestMethod.POST)
	public ModelAndView neftTransfer(HttpSession session) {
		Registration user =(Registration)session.getAttribute("user");// Retrieved through session
		String accno = user.getAccno();
		List<AddPayee> list = dao.getallpayees(accno);
		return new ModelAndView("transferForm", "list", list);
	}

	@RequestMapping(value = "/viewPayee", method = RequestMethod.POST)
	public ModelAndView viewemp(HttpSession session) {
		Registration user =(Registration)session.getAttribute("user");// Retrieved through session
		String accno = user.getAccno();
		List<AddPayee> list = dao.getallpayees(accno);
		return new ModelAndView("viewPayeeDash", "list", list);
	}

	@RequestMapping(value = "/editPayee/{id}")
	public ModelAndView editpayee(@PathVariable String id) {
		AddPayee emp = dao.getPayeeById(id);
		return new ModelAndView("payeeeditform", "command", emp);
	}

	@RequestMapping(value = "/deletePayee")
	public ModelAndView deletepayee(@ModelAttribute() AddPayee ap) {
		String id = ap.getGa_payee_accno();
		System.out.println(id);
		//int idi = Integer.parseInt(id);
		//System.out.println(idi);
		dao.deletePayee(id);
		return new ModelAndView("redirect:/refreshpage");
	}

	// Links to paymentDashboard.jsp
	@RequestMapping(value = "/refreshpage")
	public ModelAndView refreshPage() {
		return new ModelAndView("transfer");
	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("emp") AddPayee emp) {
		System.out.println("Helllo1");
		String payee_ano = emp.getGa_payee_accno();
		System.out.println(payee_ano);
		dao.update(emp, payee_ano);
		System.out.println("Helllo2");
		return new ModelAndView("payeeDashboard");
	}

	@RequestMapping(value = "/addpayee1", method = RequestMethod.POST)
	public ModelAndView addpayee(@ModelAttribute() AddPayee ap, HttpSession session) {
		Registration user =(Registration)session.getAttribute("user");// Retrieved through session
		String accno = user.getAccno();
		ap.setGa_accno(accno);
		System.out.println("Hello");
		int i = dao.addpayee(ap);
		if (i == 1) {
			System.out.println("Hello131");
			return new ModelAndView("transfer");
		} else {
			return new ModelAndView("error");
		}

	}

	@RequestMapping(value = "/transferAmount", method = RequestMethod.POST)
	public ModelAndView transfer(@ModelAttribute() TransferDetails ap, HttpSession session) {
		// session.getAttribute("user");
		// ap.setGa_sender_accno(User.getUname);
		int transfer_amount=0;
		int tr_no=0;
		try {
			transfer_amount = Integer.parseInt(ap.getGa_transfer_amount());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("reached here exception in transfer");
		}
		if (ap.getGa_transfer_mode().equals("neft") && transfer_amount > 1
				|| ap.getGa_transfer_mode().equals("rtgs") && transfer_amount > 200000 && transfer_amount < 1000000
				|| ap.getGa_transfer_mode().equals("imps") && transfer_amount > 1 && transfer_amount < 200000) {
			Registration user =(Registration)session.getAttribute("user");// Retrieved through session
			ap.setGa_sender_accno(user.getAccno());
			System.out.println("YessYamaha");
			String max_transaction_no = dao.checkMax();
			System.out.println(max_transaction_no);
			
			try {
				tr_no = Integer.parseInt(max_transaction_no) + 1;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("reached here 2");
			}
			System.out.println(tr_no);
			String str_tr_no = String.valueOf(tr_no);
			ap.setGa_transaction_no(str_tr_no);
			FlagStatus ap1 = new FlagStatus();
			ap1.setGa_transaction_no(ap.getGa_transaction_no());
			ap1.setGa_sender_accno(ap.getGa_sender_accno());
			ap1.setGa_receiver_accno(ap.getGa_receiver_accno());
			ap1.setGa_transfer_amount(ap.getGa_transfer_amount());
			ap1.setGa_flag_status("1");
			System.out.println("Hello");
			String ano = ap.getGa_sender_accno();
			int i = dao.addtransactionlog(ap);
			if (i == 1) {
				int j = dao.addflagstatus(ap1);
				if (j == 1) {
					System.out.println("Falcon1");
					int getbalance = dao.getbalancenow(ano);
					int newbal = getbalance - Integer.parseInt(ap1.getGa_transfer_amount());
					String newbal1 = String.valueOf(newbal);
					System.out.println(newbal1);
					int k = dao.updatebal(newbal1, ano);
					if (k == 1) {

						System.out.println("HelloPrbht");
						return new ModelAndView("transfer");
					} else {
						return new ModelAndView("error");
					}
				} else {
					return new ModelAndView("error");
				}
			} else {
				return new ModelAndView("error");
			}

		} else {
			return new ModelAndView("error");

		}

	}

}
