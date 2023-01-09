package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import kh.spring.dto.NetflixDTO;
import oracle.jdbc.driver.Message;

@Component
public class NetflixDAO {

	@Autowired
	private DataSource ds;
	
	public int insert(NetflixDTO dto) throws Exception{
		
		String sql="insert into netflix values(netflix_seq.nextval, ?, ?, sysdate)";

		try(Connection con = ds.getConnection();
			PreparedStatement pstat=con.prepareStatement(sql);){
			
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getGenre());
			
			
			int result =pstat.executeUpdate();
			return	 result;
		}
		
	}

	public List<NetflixDTO> selectAll() throws Exception{
		String sql = "select * from netflix order by reg_date";
		
		try(Connection con =ds.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();){
			List<NetflixDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				NetflixDTO dto = new NetflixDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setGenre(rs.getString("genre"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				
				list.add(dto);
			}
			return list;
		}
		
		
	}
	
public int delete(int seq) throws Exception{
		
		String sql="delete from netflix where seq = ? ";

		try(Connection con = ds.getConnection();
			PreparedStatement pstat=con.prepareStatement(sql);){
			
			pstat.setInt(1, seq);

			int result =pstat.executeUpdate();
			return	 result;
		}
		
	}
	
}
