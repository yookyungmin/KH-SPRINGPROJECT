package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import kh.spring.dto.FilesDTO;

@Component
public class FilesDAO {
	@Autowired //component 의 의존성 주입
	private DataSource ds;
	
	public int insertFile(FilesDTO dto) throws Exception{
		   String sql = "insert into files values(files_seq.nextval,?,?,?)";
		   try(Connection con = ds.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
		
				pstat.setString(1, dto.getOriname());
				pstat.setString(2, dto.getSysname());
				pstat.setInt(3, dto.getParent_seq());
			
				
				
				int result = pstat.executeUpdate();
		
				return result;
			}
		   
	   }
	
	
	 public List<FilesDTO> selectFileByParent(int pseq) throws Exception{
		   String sql="select * from files where parent_seq = ?";
		   try(Connection con = ds.getConnection();
				   PreparedStatement pstat = con.prepareStatement(sql);
				  
				){
			   
			   
			   pstat.setInt(1, pseq);
			   
			   try( ResultSet rs = pstat.executeQuery();){
				   	List<FilesDTO> list = new ArrayList<FilesDTO>();
				   
				   
				   while(rs.next()) {
					   FilesDTO dto = new FilesDTO();
					   dto.setSeq(rs.getInt("seq"));
					   dto.setOriname(rs.getString("oriname"));
					   dto.setSysname(rs.getString("sysname"));
					   dto.setParent_seq(rs.getInt("parent_seq"));
				
					  
					   list.add(dto);
			   }
				   return list;
			 
			   }
			
			
		   }
 
	   }
}
