package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.CommentsDTO;

@Component
public class CommentsDAO {
	
	
	@Autowired //component 의 의존성 주입
	private DataSource ds;
	
	public int insertcomment(CommentsDTO dto) throws Exception{
		String sql = "insert into comments values(comments_seq.nextval, ?, ?, sysdate, ?)";
		try(Connection con = ds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
	
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getContents());
			pstat.setInt(3, dto.getParent_seq());
		
			
			
			int result = pstat.executeUpdate();
			
			return result;
		}
	}
	
	public int commentsDelete(int seq) throws Exception{
		String sql = "delete from comments where seq = ?";
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
		
			return result;
		}
	}
	public int update(CommentsDTO dto) throws Exception{
		String sql = "update comments set contents=? where seq=?";
		try(Connection con = ds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			
			
			pstat.setString(1, dto.getContents());
			pstat.setInt(2, dto.getSeq());;
			
			System.out.println("dao con : "+dto.getContents());
			System.out.println("dao seq : "+dto.getSeq());
			
			int result = pstat.executeUpdate();
		
			return result;
		}
	}
	
   
   public List<CommentsDTO> commentsprint(int seq) throws Exception{ //selectByseq
	   String sql = "select * from comments where parent_seq =? order by write_date desc";
	   try(Connection con = ds.getConnection();
			   PreparedStatement pstat = con.prepareStatement(sql);
			){
		   
		   pstat.setInt(1, seq);
		   List<CommentsDTO> list = new ArrayList<CommentsDTO>();
		   try(ResultSet rs = pstat.executeQuery();){
			
			   
			   while(rs.next()) {
				   CommentsDTO dto=new CommentsDTO();
				 	dto.setSeq(rs.getInt("seq"));
				   dto.setWriter(rs.getString("writer"));
				   dto.setContents(rs.getString("contents"));
				   dto.setWrite_date(rs.getTimestamp("write_date"));
				   dto.setParent_seq(rs.getInt("parent_seq"));
	
				 //하나의 dto만 나오기떄문에 while문 필요x
		 
				  list.add(dto);
			   
			   }
			   return list;
		   }
		  
	   }
	   
   }
}
