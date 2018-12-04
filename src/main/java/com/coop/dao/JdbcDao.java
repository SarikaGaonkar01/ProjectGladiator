package com.coop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.coop.model.AddPayee;
import com.coop.model.DisplayTransaction;
import com.coop.model.FlagStatus;
import com.coop.model.Login;
import com.coop.model.MD5;
import com.coop.model.Registration;
import com.coop.model.TransferDetails;

public class JdbcDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	// saves user data
	public String savedata(Registration r) {

		System.out.println("test3....");
		// String sql1= "insert into gr2_details values('"+r.getAccno()+
		// "','"+r.getUname()+"','"+r.getPcard()+"','"+r.getAcard()+"','"+r.getBdetail()+"','"+r.getAdetail()+"','"+r.getAcctype()+"','"+r.getAmt()+"','"+r.getPassword()+"','"+r.getAge()+"')";
		String sql1 = "insert into gr2_details values(gd_acc_no.nextval,'" + r.getUname() + "','" + r.getPcard() + "','"
				+ r.getAcard() + "','" + r.getBdetail() + "','" + r.getAdetail() + "','" + r.getAcctype() + "','"
				+ r.getAmt() + "','" + r.getPassword() + "','" + r.getAge() + "')";
			try {
				jdbcTemplate.update(sql1);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("record not inserted");
			}
		
		
		// String sql= "insert into group2_registration(?,?,?,?,?,?,?,?,?)";
		// jdbcTemplate.update(sql, new Object[] {r.getName(), r.getAge(),r.getUname(),
		// r.getPass(), r.getAcard(), r.getPcard(), r.getBdetail(), r.getAcctype(),
		// r.getAdetail() });

		// String sql2="select max(gd_acc_no) from gr2_details";
		// String no=(String)jdbcTemplate.queryForObject(sql2,String.class);

		String accno=null;
		try {
			String sql = "select gd_acc_no.nextval from dual";
			accno = jdbcTemplate.queryForObject(sql, String.class);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("accout number not found!");
		}

		// System.out.println(accno);
		return accno;

	}

	public Registration validateUser(Login login) {
		List<Registration> users=null;
		Registration r=null;
		try {
			String sql = "select * from gr2_details where gd_name='" + login.getUname() + "' and gd_pass='"
					+ login.getPass() + "'";
			users = jdbcTemplate.query(sql, new UserMapper());
			r=users.size() > 0 ? users.get(0) : null;
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("something went wrong while validation");
		}
		return r;
		
	}

	class UserMapper implements RowMapper<Registration> {
		public Registration mapRow(ResultSet rs, int arg1) throws SQLException {
			Registration user = new Registration();
			user.setAccno(rs.getString("gd_acc_no"));
			user.setUname(rs.getString("gd_name"));
			user.setPcard(rs.getString("gd_pcard"));
			user.setAcard(rs.getString("gd_acard"));
			user.setBdetail(rs.getString("gb_gd_branch"));
			user.setAdetail(rs.getString("gd_state"));
			user.setAcctype(rs.getString("gd_acctype"));
			user.setAmt(rs.getInt("gd_amount"));
			user.setPassword(rs.getString("gd_pass"));
			user.setAge(rs.getInt("gd_age"));

			return user;
		}
	}
	
	public int search(String accno)
	{
		String sql="select gd_acc_no from gr2_details where gd_acc_no=  '"+accno+ "'  ";
		int status=0;
		try {
			String dbaccno=(String)jdbcTemplate.queryForObject(sql,String.class);
			System.out.println(dbaccno);
			if(dbaccno!= null)
				status=1;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception from search method");
		}
		System.out.println(status);
		return status;
	}
	
	public int  updatePassword(String accno,String pass)
	{
		int status=0;
		int iffound=search(accno);
		
		if(iffound==1)
		{
			String sql="update gr2_details set gd_pass= '"+pass+"'where gd_acc_no='"+accno+"' ";
			status=jdbcTemplate.update(sql);
		}
		
		return status;
	}
	
	public int updateUsername(String accno,String name)
	{
		int status=0;
		int iffound=search(accno);
		
		if(iffound==1)
		{
			String sql="update gr2_details set gd_name = '"+name+"' where gd_acc_no='"+accno+"'  ";
			status=jdbcTemplate.update(sql);
		}
		
		return status;
	}
	
	public int addpayee(AddPayee ap) {
		
		int val=0;
		try {
			String sql = "insert into gr2_payee_details(ga_accno, ga_payee_name,ga_payee_acc_ifsc, ga_payee_accno) values('"
					+ ap.getGa_accno() + "','" + ap.getGa_payee_name() + "','" + ap.getGa_payee_acc_ifsc() + "','"
					+ ap.getGa_payee_accno() + "')";
			System.out.println("DoTEst2");
			 val=jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in add payee method");
		}
		return val;

	}

	public int addtransactionlog(TransferDetails ap) {
		int val=0;
		try {
			String sql = "insert into gr2_transaction_log(ga_transaction_no, ga_sender_accno, ga_receiver_accno, ga_transfer_amount, ga_payee_remarks) values('"
					+ ap.getGa_transaction_no() + "','" + ap.getGa_sender_accno() + "','" + ap.getGa_receiver_accno()
					+ "','" + ap.getGa_transfer_amount() + "','" + ap.getGa_payee_remarks() + "')";
			System.out.println("DoTEst3");
			val= jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in addtransactionlog");
		}
		return val;

	}

	public int addflagstatus(FlagStatus ap1) {
		int val=0;
		try {
			String sql = "insert into gr2_flag(ga_transaction_no, ga_sender_accno, ga_receiver_accno, ga_transfer_amount, ga_flag_status) values('"+ ap1.getGa_transaction_no() + "','" + ap1.getGa_sender_accno()+"','" + ap1.getGa_receiver_accno() + "','" + ap1.getGa_transfer_amount() + "','" + ap1.getGa_flag_status() + "')";
			System.out.println("DoTEst4");
			val= jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in addflagstatus method");
		}
		return val;

	}

	public int update(AddPayee p, String ano) {
		
		int val=0;
		System.out.println("JDBCHello");
		try {
			String sql = "update gr2_payee_details set ga_payee_name='" + p.getGa_payee_name() + "', ga_payee_acc_ifsc='"
					+ p.getGa_payee_acc_ifsc() + "' where ga_payee_accno="
					+ ano + "";
			val= jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in update method");
		}
		return val;
	}

	public String check(String ano) {
		System.out.println("JDBCHello");
		String name=null;
		try {
			String sql = "select ga_flag_status from gr2_flag where gd_sender_accno=" + ano + "";
			name = (String) getJdbcTemplate().queryForObject(sql, new Object[] { ano }, String.class);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in check method");
		}
		return name;
	}
	
	
	public String checkMax() {
		//System.out.println("JDBCHello");
		String name=null;
		try {
			String sql = "select max(ga_transaction_no) from gr2_transaction_log";
			name = (String) getJdbcTemplate().queryForObject(sql, String.class);
			System.out.println(name);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in the checkMax method");
		}
		return name;
	}
	
	
	public int updatenewbalance(String accno, int newbal) {
		int val=0;
		System.out.println("JDBCHello");
		try {
			String newbali = String.valueOf(newbal);
			String sql = "update gr2_details set gd_amount='" + newbali + "' where gd_acc_no=" + accno + "";
			val= jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in updateNewBalance method");
		}
		return val;
	}
	
	/*
	public int updatebalance(String nbal, String ano) {
		System.out.println("JDBCHello");
		String sql = "update gr2_details set gd_amount='" + nbal + "' where gd_acc_no=" + ano + "";
		return jdbctemplate.update(sql);
	}
	*/

	public int updatebal(String p, String ano) {
		
		int val=0;
		try {
			System.out.println("JDBCHello28");
			String sql = "update gr2_details set gd_amount='" +p+ "' where gd_acc_no=" + ano + "";
			val= jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in updateBal method");
		}
		
		return val;
	}
	

	public FlagStatus validateBalance(String accno) {
		int ak = 1;
		List<FlagStatus> users=null;
		FlagStatus fs=null;
		try {
			String sql = "select * from gr2_flag where ga_receiver_accno='" + accno + "' and ga_flag_status='"+ak+"'";
			users = jdbcTemplate.query(sql, new UserMapper1());
			fs=users.size() > 0 ? users.get(0) : null;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in the validateBalance method");
		}
		return fs;
	}

	class UserMapper1 implements RowMapper<FlagStatus> {
		public FlagStatus mapRow(ResultSet rs, int arg1) throws SQLException {
			FlagStatus user = new FlagStatus();
			user.setGa_transaction_no(rs.getString("ga_transaction_no"));
			user.setGa_sender_accno(rs.getString("ga_sender_accno"));
			user.setGa_receiver_accno(rs.getString("ga_receiver_accno"));
			user.setGa_transfer_amount(rs.getString("ga_transfer_amount"));
			user.setGa_flag_status(rs.getString("ga_flag_status"));

			return user;
		}
	}

	public List<AddPayee> getallpayees(String accno) {
		List<AddPayee> lst=null;
		
		try {
			lst= jdbcTemplate.query("select * from gr2_payee_details where ga_accno='"+accno+"'", new RowMapper<AddPayee>() {
				public AddPayee mapRow(ResultSet rs, int row) throws SQLException {
					AddPayee e = new AddPayee();
					e.setGa_accno(rs.getString(1));
					e.setGa_payee_name(rs.getString(2));
					e.setGa_payee_acc_ifsc(rs.getString(3));
					e.setGa_payee_accno(rs.getString(4));
					return e;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception in getallpayees method");
		}
		return lst;
	}
	public List<DisplayTransaction> getalltransaction(String accno) {
		List<DisplayTransaction> lst=null;
		
		try {
			lst= jdbcTemplate.query("select * from gr2_transaction_log where ga_sender_accno='"+accno+"'", new RowMapper<DisplayTransaction>() {
				public DisplayTransaction mapRow(ResultSet rs, int row) throws SQLException {
					DisplayTransaction e = new DisplayTransaction();
					System.out.println("Why Not working");
					e.setGa_sender_accno(rs.getString(2));
					e.setGa_receiver_accno(rs.getString(3));
					e.setGa_payee_amount(rs.getString(4));
					e.setGa_payee_remark(rs.getString(5));
					
					return e;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception in getallpayees method");
		}
		return lst;
	}
	/*
	public List<DisplayTransaction> getalltransaction(String accno) {
		List<DisplayTransaction> lst=null;
		try {
			lst= jdbcTemplate.query("select * from gr2_transaction_log where ga_receiver_accno='"+accno+"'", new RowMapper<DisplayTransaction>() {
				public DisplayTransaction mapRow(ResultSet rs, int row) throws SQLException {
					DisplayTransaction e = new DisplayTransaction();
					System.out.println("********************************");
					String sql = "select gd_name from gr2_details where gd_acc_no=?";
					String name = (String) getJdbcTemplate().queryForObject(sql, new Object[] { rs.getString(2) }, String.class);
					e.setGa_sender_name(name);
					System.out.println(e.getGa_sender_name());
					System.out.println(e.getGa_payee_amount());
					e.setGa_sender_accno(rs.getString(3));
					e.setGa_payee_amount(rs.getString(4));
					e.setGa_payee_remark(rs.getString(5));
					return e;
					
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception in getallTransaction method");
		}
		return lst;
	}
	*/
	

	public int deletePayee(String id) {
		int val=0;
		try {
			String sql = "delete from gr2_payee_details where ga_payee_accno=" + id + "";
			System.out.println(id);
			System.out.println("query executed" + id + " " + sql);

			val= jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in deletePayee method");
		}
		return val;
	}
	

	public AddPayee getPayeeById(String id) {
		AddPayee ap=null;
		try {
			String sql = "select * from gr2_payee_details where ga_payee_accno=?";
			ap= jdbcTemplate.queryForObject(sql, new Object[] { id },
					new BeanPropertyRowMapper<AddPayee>(AddPayee.class));
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in getPayeeById method");
		}
		return ap;
	}

	public int getbalancenow(String id) {
		//int id1 = Integer.parseInt(id);
		int name=0;
		try {
			String sql = "select gd_amount from gr2_details where gd_acc_no=?";
			name = (Integer) getJdbcTemplate().queryForObject(sql, new Object[] { id }, Integer.class);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in getbalancenow method");
		}
		return name;
	}
	
	
	public int deleteflag(int id) {
		
		int val=0;
		try {
			String sql = "delete from gr2_flag where ga_transaction_no=" + id + "";
			System.out.println(id);
			System.out.println("query executed" + id + " " + sql);
            val= jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("exception occured in delete flag method");
		}
		
		return val;
	}
	
	
}
