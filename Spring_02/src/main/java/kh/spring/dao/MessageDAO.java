package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MessageDTO;

@Repository
public class MessageDAO {
	
	
	//MyBatis 코드

	@Autowired
	private SqlSession db;
	
	public int insert(MessageDTO dto) {
		db.insert("Message.insert", dto);
		return dto.getSeq(); 
	}
	
	public int delete(int delete) {
		return db.delete("Message.delete",delete);
	}
	
	public MessageDTO selectBySeq(int seq) {
		return db.selectOne("Message.selectBySeq", seq);
	}

	public List<MessageDTO> selectByCon(Map<String,String>  param){
		return db.selectList("Message.selectByCon", param);
	}
	
	public List<MessageDTO> selectByMultiCon(MessageDTO dto){
		return db.selectList("Message.selectByMultiCon",dto);
	}
	
	public List<MessageDTO> selectAll(){
		return db.selectList("Message.selectAll");
	}
	
	
////	public int insert(MessageDTO dto) {
////		return db.insert("Message.insert", dto);
////	}
//	
//	public int insert(MessageDTO dto) {
//		db.insert("Message.insert", dto);
//		System.out.println(dto.getSeq());
//		return dto.getSeq();
//	}//getNextval
//	
//	public List<MessageDTO> selectAll(){
//		return db.selectList("Message.selectAll");
//		
//	} //반환타입 list일떄
//	
//	public int delete(int seq) {
//		return db.delete("Message.delete", seq);
//	}
//
//	public MessageDTO selectBySeq(int seq) {
//		return db.selectOne("Message.selectBySeq", seq);
//		
//	} //list가 아닌 dto 가 반환타입일떄
//
//	public List<MessageDTO> selectByCon(String condition, String keyword) {
//		Map<String, String> param=new HashMap<>();
//		param.put("condition", condition);
//		param.put("keyword", keyword); //key value
//		return db.selectList("Message.selectByCon",param);
//	}
//
//	public List<MessageDTO> selectByMulticon(String writer, String message) {
//		MessageDTO dto= new MessageDTO(0, writer, message);
//		return db.selectList("Message.selectByMultiCon", dto);
//	}
	
	//dao의 매개변수가 두개 이상 필요하면 mybatis는 어덯게 처리할것인가? map사용

	//Spring jdbc
//	@Autowired
//	private JdbcTemplate jdbc; //@comoponent의 의존성주입은 방법은 @Autowired를붙여줌
//	
//	//@Autowired
////	private DataSource ds
//
//	public int insert(MessageDTO dto) {		
//		String sql= "insert into message values(message_seq.nextval, ?, ?)";
//		return jdbc.update(sql, dto.getWriter(), dto.getMessage());
//	}
//	
//	public int delete(int seq) {
//		String sql = "delete from message where seq=?";
//		return jdbc.update(sql, seq);
//	}
//	
//	public int update(MessageDTO dto) {
//		String sql = "update message set writer=?, message=? where seq=?";
//		return jdbc.update(sql, dto.getWriter(), dto.getMessage(), dto.getSeq());
//	}
//	
//	public List<MessageDTO> selectAll(){ //List를 select 할경우 query
//		String sql = "select * from message";
//		return jdbc.query(sql, new RowMapper<MessageDTO>() {
//			 @Override
//			public MessageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				MessageDTO dto = new MessageDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setMessage(rs.getString("message"));
//				return dto;
//			 }//익명클래스 콜백함수
//		});
//		//메서드를 오버라이딩한 인스턴스를 콜백으로 넘긴다?
//		//rs.next 한바퀴 돌떄마다 그 값들을 어떻게 할건지를 정하는 콜백을 넣어줘야 한다.
//		//익명클래스 콜백, RowMapper는 인터페이스인데 
//		//추상클래스, 인터페이스가 new를 할수 없는 이유는 추상메서드가 있기 때문에
//	}
//	
//	public List<MessageDTO> selectAlll(){ //List를 select 할경우 query
//		String sql = "select * from message where seq between ? and ?";
//		return jdbc.query(sql, new RowMapper<MessageDTO>() {
//			 @Override
//			public MessageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				MessageDTO dto = new MessageDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setMessage(rs.getString("message"));
//				return dto;
//			 }//익명클래스 콜백함수
//		 //deprecated 언젠가 사라질수 있는 메서드
//		},1,10);
//		//메서드를 오버라이딩한 인스턴스를 콜백으로 넘긴다?
//		//rs.next 한바퀴 돌떄마다 그 값들을 어떻게 할건지를 정하는 콜백을 넣어줘야 한다.
//		//익명클래스 콜백, RowMapper는 인터페이스인데 
//		//추상클래스, 인터페이스가 new를 할수 없는 이유는 추상메서드가 있기 때문에
//	}
//	
//	//단 한개의 DTO 또는 INT 값 또는 select 할 경우 queryForObject
//	public MessageDTO selectBySeq(int seq) {
//		String sql = "select * from message wehre seq=?";
//		return jdbc.queryForObject(sql, new RowMapper<MessageDTO>() {
//			 @Override
//			public MessageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				MessageDTO dto = new MessageDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setMessage(rs.getString("message"));
//				return dto;
//			 }
//		},seq);
//	}
//	
//	public int selectCount() {
//		String sql = "select count(*) from message";
//		return jdbc.queryForObject(sql, Integer.class);
//	}
	
	
	
	
	//기본jdbc
//	public int insert(MessageDTO dto) throws Exception{
//		String sql = "insert into message values(message_seq.nextval, ?, ?)";
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstat = con.prepareStatement(sql)){
//			
//			pstat.setString(1, dto.getWriter());
//			pstat.setString(2, dto.getMessage());
//			return pstat.executeUpdate();
//			
//			//ojdbc8 버전은 commit을 자동으로 해준다
//			
//		}
//	}
//	public int delete(int seq) throws Exception{
//		String sql = "delete from message where seq=?";
//		
//		try(Connection con = ds.getConnection();
//				PreparedStatement pstat=con.prepareStatement(sql)){
//			pstat.setInt(1, seq);
//			
//			int result = pstat.executeUpdate();
//			return result;
//			
//		}
//	}
//	public List<MessageDTO> selectAll() throws Exception {
//		String sql="select * from message";
//		
//		try(Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				ResultSet rs = pstat.executeQuery();){
//			
//			List<MessageDTO> list = new ArrayList<MessageDTO>();
//			
//			while(rs.next()) {
//				MessageDTO dto = new MessageDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setMessage(rs.getString("message"));
//				list.add(dto);
//		}
//			return list;
//		}
//		
//			
//}
	

}
