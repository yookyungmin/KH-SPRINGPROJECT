package kh.spring.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file/")
public class FileController {

		@Autowired
		private HttpSession session;
		@RequestMapping("upload")
		public String upload(String writer, String message, MultipartFile file) throws Exception {
			System.out.println(writer);
			System.out.println(message);
			//board.insert(writer, message); //보드테이블작업
			
			System.out.println(file);
			
			String realPath=session.getServletContext().getRealPath("upload");
			File filePath=new File(realPath);
			if(!filePath.exists()) {
				filePath.mkdir(); //파일업로드 폴더가 없다면 생성
			}
			String oriName=file.getOriginalFilename();
			//겹치지 않게 이름을 만들어야함
			String sysName=UUID.randomUUID()+"_"+oriName;
			
			//파일이 넘어오면 어딘가에 저장이 되어서 복사해줘야됨
		//	file.transferTo(filePath+"/"+sysName);
			file.transferTo(new File(filePath+"/"+sysName));//
			
			//fdao.insert(new File(DTO(file.nextval, oriName, sysName, nextval));
			
			return "redirect:/";
		}
	
		@RequestMapping("multiUpload")
		public String multiUpload(String writer, String message, MultipartFile[] files) throws Exception {
			System.out.println(writer);
			System.out.println(files);
			String realPath = session.getServletContext().getRealPath("upload");
			File filePath=new File(realPath);
			if(!filePath.exists()) {
				filePath.mkdir(); //파일업로드 폴더가 없다면 생성
			}
			System.out.println(files.length);
			
			// MultipartFile[] files 와 names이름이 다르면null값
			
			if(!files[0].getOriginalFilename().equals("")) { // 0인덱스에 파일이 없지않다면 아래 실행
			for(MultipartFile file : files) {
				if(file.getOriginalFilename().equals("")) {continue;}  //null값이면 건너듸기
				String oriName=file.getOriginalFilename();
				//겹치지 않게 이름을 만들어야함
				String sysName=UUID.randomUUID()+"_"+oriName;
				file.transferTo(new File(filePath+"/"+sysName));//
			}
			}
			return "redirect:/";
		}
		
		@RequestMapping("download")
		public void download(String oriname, String sysname, HttpServletResponse resp) throws Exception{
			
			String realPath=session.getServletContext().getRealPath("upload");
			File targetFile=new File(realPath+"/"+sysname);
			//파일의 위치+다운받을 파일 이름으로 대상특정
			
			
			oriname = new String(oriname.getBytes("utf8"), "utf8");
			resp.setHeader("content-disposition", "attachment; filsname=\"" +oriname+"\";");
			//응답데이터가 첨부파일임을 알림, 다운로드 파일이름ㅣㅣ

		try(
			DataInputStream dis= new DataInputStream(new FileInputStream(targetFile));
			DataOutputStream dos = new DataOutputStream(resp.getOutputStream())){
		
			byte[] fileContents= new byte[(int)targetFile.length()];
			dos.write(fileContents);
			dos.flush();
		}
		}
		
		@ExceptionHandler(Exception.class)
		public String exceptionHandler(Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		
		
}
