package kh.spring.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/files/")
public class FilesController {

	@Autowired
	private HttpSession session;
	@RequestMapping("download")
	public void download(String oriname, String sysname, HttpServletResponse resp) throws Exception{
		
		System.out.println(oriname+sysname);
		String realPath=session.getServletContext().getRealPath("upload");
		File targetFile=new File(realPath+"/"+sysname);
		//파일의 위치+다운받을 파일 이름으로 대상특정
		
		
		oriname = new String(oriname.getBytes("utf8"), "utf8");
		System.out.println(oriname+sysname);
		resp.setHeader("content-disposition", "attachment; filsname=\"" +oriname+"\";");
		//응답데이터가 첨부파일임을 알림, 다운로드 파일이름ㅣㅣ

	try(
		DataInputStream dis= new DataInputStream(new FileInputStream(targetFile));
		DataOutputStream dos = new DataOutputStream(resp.getOutputStream())){
	
		byte[] fileContents= new byte[(int)targetFile.length()];
		dis.readFully(fileContents);
		dos.write(fileContents);
		dos.flush();
	}
	}
}
