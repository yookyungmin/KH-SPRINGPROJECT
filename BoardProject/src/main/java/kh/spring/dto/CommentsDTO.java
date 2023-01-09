package kh.spring.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommentsDTO {
	private int seq;
	private String writer;
	private String contents;
	private Timestamp write_date;
	private int parent_seq;

	
	
	
	public int getSeq() {
		return seq;
	}




	public void setSeq(int seq) {
		this.seq = seq;
	}




	public String getWriter() {
		return writer;
	}




	public void setWriter(String writer) {
		this.writer = writer;
	}




	public String getContents() {
		return contents;
	}




	public void setContents(String contents) {
		this.contents = contents;
	}




	public Timestamp getWrite_date() {
		return write_date;
	}




	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}




	public int getParent_seq() {
		return parent_seq;
	}




	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}




	public CommentsDTO(int seq, String writer, String contents, Timestamp write_date, int parent_seq) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.contents = contents;
		this.write_date = write_date;
		this.parent_seq = parent_seq;
	}




	public CommentsDTO() {
		super();
	}




	public String getFormDate() {
		
		long writeTime = this.write_date.getTime();
		long currentTime = System.currentTimeMillis();
		
		//System.currentTimeMillis()
		//1970년 1월 1일부터 경과한 시간을 long값으로 리턴 1/1000 초값을리턴
		long timeGap = currentTime - writeTime;
//		System.out.println(timeGap);
		
		if(timeGap<60*1000) {
			return "1분 이내";
		}else if(timeGap <300*1000) {
			return "5분 이내";
		}else if(timeGap <3600*1000) {
			return "1시간 이내";
		}else if(timeGap < 86400*1000) {
			return "24시간 이내";
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("MM월dd일 hh시");
			//SimpleDateFormat sdf = new SimpleDateFormat("MM월dd일 hh시mm분");
			return sdf.format(writeTime);
		}
	

	}
	
}
