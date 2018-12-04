package com.coop.dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.coop.model.Loan;
import com.coop.model.Registration;

public class LoanDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//saves user loan request in the database
	public int  save(Loan l)
	{
		int seq=0;  
		try {
			System.out.println("Loan db....");
			  String sql= "insert into gr2_loans values(gr2_loans_seq.nextval,'"+l.getB_id()+"','"+l.getAcc_no()+"','"+l.getLoantype()+"','"+l.getRate()+"','"+l.getTime()+"','"+l.getAmt()+"','"+l.getStatus()+"')";
			  System.out.println("LoanDao test 2........Insert__save");
			  jdbcTemplate.update(sql);
			  
			  System.out.println("Sequence_no:"+seq);
			  
		   } catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in save method");
		}	
		  
		seq=getSequence(l.getAcc_no());
		return seq;
			
	}
	
	//returns loan_id
	public int getSequence(String accno)
	{
		String strseq=null;
		int seq=0;
		try {
			String sql="select max(gl_loan_id) from gr2_loans where gl_acc_no=?";
			strseq = (String)jdbcTemplate.queryForObject(sql,new Object[] {accno},String.class);
			seq=Integer.parseInt(strseq);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in getSequence method");
		}
		
		
		return seq;
	}
	
	
	
	//reject application if cibil criteria is not satisfied
	public int rejectRequest(int loanid)
	{
		int result=0;
		try {
			String sql="delete from gr2_loans where gl_loan_id='"+loanid+"'  ";
			result = (Integer)jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occcured in  rejectRequest method");
		}
		return result;
	}
	
	
	
	//checks cibil score
	public int getCibil(Registration r)
	{
		int cibil=0;
		int flag=0;
		
		try {
			System.out.println("LoanDao test 3......getCibil");
			String sql="select gc_score from  gr2_cibils where gc_acc_no=?"; 
			System.out.println("LoanDao test 4......Cibil");
			cibil = (int)jdbcTemplate.queryForObject(sql, new Object[]{r.getAccno()},Integer.class);
			System.out.println("LoanDao test 5......Selectquery from getCibil");
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in getCibil method");
		}
	    
	    
	    if(cibil >750)
	    	flag=1;
	    else
	    	 flag=0;
	     
	    return flag;
	
	}
	
	
	

	
}
