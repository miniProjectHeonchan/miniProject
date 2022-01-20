package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WordListDAO {

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
	
	//문제 뜻 불러오는 메소드
	public  ArrayList<WordListVO> wordList(int yearNum) {
		//jdbc 드라이버 불러오기
		connect();
		
		ArrayList<WordListVO> al = new ArrayList<WordListVO>();
		
		
		try {
			String sql = "SELECT * FROM WORD_LIST WHERE YEAR = ?";
			
			//4. sql 구문 준비 객체(PreparedStatement) 생성
			pst = conn.prepareStatement(sql);
			pst.setInt(1, yearNum);
			//5. sql문을 실행하고 결과 처리
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				String word = rs.getString("WORD"); //현재 커서가 가르키고 있는 행의 첫번째 컬럼값을 읽어오겠다!
				String mean = rs.getString("MEAN"); //컬럼이름과 일치하게 작성
				int year = rs.getInt("YEAR");
				String hint1 = rs.getString("HINT1");
				String hint2 = rs.getString("HINT2");
				
				//위에서 읽어온 값들로 초기화시켜 생성한 StudentVO 객체의 참조값을
				//ArrayList에 추가
				al.add(new WordListVO(word, mean, year, hint1, hint2));
				
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return al;
	}
}
