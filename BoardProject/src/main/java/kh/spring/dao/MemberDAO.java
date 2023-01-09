package kh.spring.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MemberDTO;


@Repository
public class MemberDAO {
//		@Autowired //component 의 의존성 주입
//		private DataSource ds;
//		
//		@Autowired
//		private JdbcTemplate jdbc;
//		
	
	@Autowired
	private SqlSession db;
	
	public int insertSign(MemberDTO dto) {
		return db.insert("Member.insert", dto);

	
	}
	public boolean idCheck(String id) {
		return db.selectOne("Member.idcheck", id);
	}
	
	 public boolean isLoginOk(Map<String,String> param) {
		 System.out.println("로그인dao진입");
		boolean result= db.selectOne("Member.isLoginOk", param);
		System.out.println(result+"dao결과");
		return result;

	 }
	 
	 public MemberDTO selectMypage(String id)  {
		 
		 
				 MemberDTO a = db.selectOne("Member.mypage",id);
				 System.out.println(a.getEmail());
				 return a;		 
	 }
	 
	 public int deleteMember(String id) {
		 System.out.println(id+"삭제");
		 return db.delete("Member.delete", id);
	 }
	
	 public int myPageUpdate(MemberDTO dto) {
		 return db.update("Member.update", dto);
	 }
	 
		
//		 public int insertSign(MemberDTO dto, String sysname) {
//				String sql = "insert into members values(?, ?, ?, ?, ?, ?, ?,?,sysdate,?)";
//				return jdbc.update(sql, dto.getId(), getSha256(dto.getPw()), dto.getName(), dto.getEmail(), dto.getEmail(),
//						dto.getZipcode(), dto.getAddress1(), dto.getAddress2(), sysname);
//				}
		
//		 public MemberDTO selectMypage(String id)  {
//			 String sql = "select * from members where id = ?";
//			 return jdbc.queryForObject(sql, new RowMapper<MemberDTO>() {
//				 
//				 @Override
//				public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//					 MemberDTO dto = new MemberDTO();
//					 dto.setId(rs.getString("id"));
//					   dto.setPw(rs.getString("pw"));
//					   dto.setName(rs.getString("name"));
//					   dto.setPhone(rs.getString("phone"));
//					   dto.setEmail(rs.getString("email"));
//					   dto.setZipcode(rs.getString("zipcode"));
//					   dto.setAddress1(rs.getString("address1"));
//					   dto.setAddress2(rs.getString("address2"));
//					   dto.setSignup_date(rs.getTimestamp("signup_date"));
//					   dto.setProfile_img(rs.getString("profile_img"));
//					return dto;
//				}
//				 
//				 
//			 }, id);
//		 }
		
//		   public String getProfile(String id) throws Exception {
//			      String sql = "select profile_img from members where id = ?";
//			      if(jdbc.queryForObject(sql, String.class, id)!= null || jdbc.queryForObject(sql, String.class, id)!= "") {
//			         System.out.println(jdbc.queryForObject(sql, String.class, id));
//			         return jdbc.queryForObject(sql, String.class, id);
//			      }else {
//			         return "no_profile.png";
//			      }
//			   }
		   
//		   public String getProfile(String id) throws Exception {
//			      String sql = "select profile_img from members where id = ?";
//			    
//			         return jdbc.queryForObject(sql, String.class, id);
//			
//			   }
//		 
//		   
//			public int update(MemberDTO dto,String sysname) throws Exception{
//			String sql = "update members set pw=?, name=?, phone=?, email=?, zipcode=?, address1=?, address2=?, profile_img = ? where id= ?";
//			return jdbc.update(sql, dto.getPw(), dto.getName(), dto.getPhone(), dto.getEmail(), dto.getZipcode(), dto.getAddress1(),
//					dto.getAddress2(), sysname, dto.getId());
//			}
		
		
		
//		public boolean idCheck(String id) throws Exception { //id 중복체크
//			String sql = "select *from members where id = ?";
//			
//			try(Connection con = ds.getConnection();
//					PreparedStatement pstat = con.prepareStatement(sql); ){
//				
//				
//				pstat.setString(1, id);
//				
//				try(ResultSet rs = pstat.executeQuery()){
//					boolean result = rs.next(); //id가 있으면 true;
//					return result;
//				}
//				
//			}
//				
//		}
		
//		 public int insertSign(MemberDTO dto, String sysname) throws Exception{
//				String sql = "insert into members values(?, ?, ?, ?, ?, ?, ?,?,sysdate,?)";
//				try(Connection con = ds.getConnection();
//						PreparedStatement pstat = con.prepareStatement(sql);){
//					pstat.setString(1, dto.getId());
//					pstat.setString(2, getSha256(dto.getPw()));
//					pstat.setString(3, dto.getName());
//					pstat.setString(4, dto.getPhone());
//					pstat.setString(5, dto.getEmail());
//					pstat.setString(6, dto.getZipcode());
//					pstat.setString(7, dto.getAddress1());
//					pstat.setString(8, dto.getAddress2());
//					pstat.setString(9, sysname);
//					int result = pstat.executeUpdate();
//				
//					return result;
//				}
//			}

//		 public boolean isLoginOk(String idd, String pww) throws Exception{  //id중복확인 dao
//			   String sql = "select id, pw from Members where id = ? and pw=?";
//			   try(Connection con = ds.getConnection();
//						PreparedStatement pstat = con.prepareStatement(sql);
//					  ){
//				   pstat.setString(1, idd);
//				   pstat.setString(2, pww);
//				   try( ResultSet rs = pstat.executeQuery();){
//					   boolean result = rs.next(); //id가 존재하면 true
//						return result;
//						//return rs.next(); 가능
//				   }
//		
//			   }
//		 }
		 
//		 public MemberDTO selectMypage(String id) throws Exception {
//			 String sql = "select * from members where id = ?";
//			 try(Connection con = ds.getConnection();
//					 PreparedStatement pstat = con.prepareStatement(sql);){
//				 
//				 pstat.setString(1, id);
//				 try(ResultSet rs = pstat.executeQuery();){
//					 MemberDTO dto = new MemberDTO();
//					 
//					 rs.next();
//					 dto.setId(rs.getString("id"));
//					   dto.setPw(rs.getString("pw"));
//					   dto.setName(rs.getString("name"));
//					   dto.setPhone(rs.getString("phone"));
//					   dto.setEmail(rs.getString("email"));
//					   dto.setZipcode(rs.getString("zipcode"));
//					   dto.setAddress1(rs.getString("address1"));
//					   dto.setAddress2(rs.getString("address2"));
//					   dto.setSignup_date(rs.getTimestamp("signup_date"));
//					   dto.setProfile_img(rs.getString("profile_img"));
//			 
//					   return dto;
//				 }
//			 }
//		 }
		 
//			public int update(MemberDTO dto) throws Exception{
//				String sql = "update members set pw=?, name=?, phone=?, email=?, zipcode=?, address1=?, address2=? where id= ?";
//				System.out.println(dto.getId()+"dao");
//				try(Connection con = ds.getConnection();
//						PreparedStatement pstat = con.prepareStatement(sql);){
//					
//					pstat.setString(1, getSha256(dto.getPw()));
//					pstat.setString(2, dto.getName());
//					pstat.setString(3, dto.getPhone());
//					pstat.setString(4, dto.getEmail());
//					pstat.setString(5, dto.getZipcode());
//					pstat.setString(6, dto.getAddress1());
//					pstat.setString(7, dto.getAddress2());
//					pstat.setString(8, dto.getId());
//					
//					int result = pstat.executeUpdate();
//					
//					return result;
//				}
//			}
			
//			public int update(MemberDTO dto,String sysname) throws Exception{
//				String sql = "update members set pw=?, name=?, phone=?, email=?, zipcode=?, address1=?, address2=?, profile_img = ? where id= ?";
//				System.out.println(dto.getId()+"dao");
//				try(Connection con = ds.getConnection();
//						PreparedStatement pstat = con.prepareStatement(sql);){
//					
//					pstat.setString(1, getSha256(dto.getPw()));
//					pstat.setString(2, dto.getName());
//					pstat.setString(3, dto.getPhone());
//					pstat.setString(4, dto.getEmail());
//					pstat.setString(5, dto.getZipcode());
//					pstat.setString(6, dto.getAddress1());
//					pstat.setString(7, dto.getAddress2());
//					pstat.setString(8, sysname);
//					pstat.setString(9, dto.getId());
//					
//					int result = pstat.executeUpdate();
//					
//					return result;
//				}
//			}
		 
		
		
		
}
