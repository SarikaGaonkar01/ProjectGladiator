 package com.coop.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.coop.model.Asset;

public class AssetDao {
	
	@Autowired
	JdbcTemplate db;
	
	
	public JdbcTemplate getDb() {
		return db;
	}


	public void setDb(JdbcTemplate db) {
		this.db = db;
	}
	
	//saves asset details in the database.
	public int saveAsset(Asset a)
	{
		int seq=0;
		String sql="insert into gr2_assets values(gr2_assets_seq.nextval,'"+a.getAccno()+"','"+a.getAsset()+"' ,'"+a.getAval()+"','" +a.getYield()+"','"+a.getYval()+"','"+a.getAyield()+"')";
		try {
			System.out.println("reached here buddy");
			db.update(sql);
			System.out.println("after this statement");
			seq=getSequence(a.getAccno());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e);
			System.out.println("exception occured in saveAsset method");
		}
		
		
		return seq;
	}
	
	public int getSequence(String accno)
	{
		String strseq=null;
		int seq=0;
		try {
			String sql="select max(ga_asset_id) from gr2_assets where ga_accno=?";
			strseq = (String)db.queryForObject(sql, new Object[] {accno},String.class);
			seq=Integer.parseInt(strseq);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in getSequence method");
		}
	   
		return seq;
	}


	public int checkAsset(Asset a,int loan_id)
	{
		String accno=a.getAccno();
	//	int lid=(Integer) session.getAttribute("loanid");
		
		int age=0;
		String loan_time=null;
		int yield_val=0;
		int loan_amount=0;
		int bank_balance=0;
		int asset_value=0;
		
		int flag=0;
		
		try {
			String sql1="select gd_age from gr2_details where gd_acc_no=?";
			age = (Integer)db.queryForObject(sql1,new Object[] {accno},Integer.class);
			System.out.println("age:"+age);
			
			String sql2="select gl_time from gr2_loans where gl_acc_no=? and gl_loan_id=? ";
			loan_time = (String)db.queryForObject(sql2,new Object[] {accno,loan_id },String.class);
			System.out.println("loan_time"+loan_time);
			
			
			
			//String sql2="select gl_time from gr2_loans where gl_acc_no=?";
			//String loan_time=(String)db.queryForObject(sql2,new Object[] {accno},String.class);
			
			
			
			String sql3="select ga_yield_value from gr2_assets where ga_accno=? and ga_asset_id=?";
			yield_val = (Integer)db.queryForObject(sql3,new Object[] {accno,a.getAsset_id()},Integer.class);
			
			System.out.println("yield_val:"+yield_val);
			
			String sql4="select gl_amount from gr2_loans where gl_acc_no=? and gl_loan_id=?";
			loan_amount = (Integer)db.queryForObject(sql4,new Object[] {accno,loan_id},Integer.class);
			System.out.println("loan_amount:"+loan_amount);
			
			String sql5="select gd_amount from gr2_details where gd_acc_no=?";
			bank_balance = (Integer)db.queryForObject(sql5,new Object[] {accno},Integer.class);
			System.out.println("bank_balance:"+bank_balance);
			
			String sql_6="select ga_asset_value from gr2_assets where  ga_accno=? and ga_asset_id=?";
			asset_value = (Integer)db.queryForObject(sql_6,new Object[] {accno,a.getAsset_id()},Integer.class);
			System.out.println("asset_value:"+asset_value);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in checkAsset method");
		}
		
		
		
		
		
		//2.checks condition
		if(age>50 && loan_time!="lta")
		{
			return flag;
		}

		//3.checks capacity
		if((0.4*yield_val)>loan_amount)
		{
			//4.checks capacity
			if(bank_balance>0.05*loan_amount)
			{
				//5.checks collateral
				if(asset_value>loan_amount)
				{
                   flag=1;
                   return 1;
				}	  
			}		
		
		}
		
		return flag;
		
			  
				  
			
		}
	
	//approves status of the loan application
	public int approveLoan(int loan_id,String ano)
	{
		int result=0;
		
		try {
			String sql="update gr2_loans set gl_status='"+1+"' where gl_loan_id='"+loan_id+"' and gl_acc_no='"+ano+"' ";
			 result=db.update(sql);
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in the approve loan method");
		}
		
		return result;
		
	}
	//rejects loan application
	public int rejectRequest(int loanid)
	{
		int result=0;
		try {
			String sql="delete from gr2_loans where gl_loan_id='"+loanid+"' and gl_status='"+0+"'  ";
			result=(Integer)db.update(sql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in the rejectRequest method");
		}
		return result;
	}
		
		
		
	}


