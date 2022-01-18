package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "campus_d_5_0115";
			String password = "smhrd5";
			
			//2. 사용할 계정 선택, db 연결 객체(Connection) 생성
			conn = DriverManager.getConnection(url, user, password);			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//사용한 객체들 반환(종료)
	public void close() {
		try {
			if(rs != null) {
				rs.close(); //selectStds(), selectOneStd()에서만 사용하는 객체				
							//ResultSet 객체가 생성되었을때만 호출 가능한 메서드
			}
			if(pst != null) {
				pst.close();				
			}
			if(conn != null) {
				conn.close();				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertUser(String id, int password) {
		boolean check = false;
		
		try {
			//jdbc 드라이버 불러오기
			connect();
			
			String sql = "INSERT INTO USER_INFO VALUES(?, ?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setInt(2, password);
			pst.setInt(3, 0);
			
			int cnt = pst.executeUpdate();
			
			if(cnt>0) {//추가 성공
				check = true;
			}else { //추가 실패 
				check = false;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return check;
	}// end of insertUser
	
	
	
}//end of class
