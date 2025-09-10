package lx.edu.springboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lx.edu.springboot.vo.AddrBookVO;


@Component
public class AddrBookDAO {

	@Autowired
	SqlSession session;
	
	public int insertDB(AddrBookVO ab) throws Exception {
		return session.insert("insertDB", ab); // mapper의 아이디 값을 넣어야하는데, 그 아이디값을 우리는 메서드이름으로 넣었다.. 모두 그러나?
	}

	public List<AddrBookVO> getDBList() throws Exception {
		
		return session.selectList("getDBList");
	}

	// 근데 나는 getOne을 이미 만들엇어 그래서 얜 똑같은 거니까 안쓸거임
	public AddrBookVO getDB(int abId) throws Exception {
		return session.selectOne("getDB",abId);
	}
	
	// 수정 sql을 받는 메서드 -> 근데 영향을 받은 행을 리턴해야하나봄 얘도 insert처럼
	public int updateDB(AddrBookVO vo) throws Exception {
		//return session.update("modifyDB", vo);
		return session.update("updateDB",vo);
	}
	
	public AddrBookVO getOne(int abId) throws Exception {
	    return session.selectOne("getOne", abId);
	}
	
	public boolean deleteDB(int abId) throws Exception {
		boolean result = false;
		int deletedAmount = session.delete("deleteDB", abId);
		
		if(deletedAmount > 0) result = true;
		session.commit();
		return result;
		
//		System.out.println("deleteDB 함수 호출" + abId);
//		boolean message = false;
//		Connection con = getConnection();
//		String sql = "delete from addrbook where ab_id =?";
//		PreparedStatement psmt = con.prepareStatement(sql);
//		psmt.setInt(1, abId);
//		psmt.execute();
//		con.close();
//		
//		message = true;
//		
//		return message;
	}
	
}
