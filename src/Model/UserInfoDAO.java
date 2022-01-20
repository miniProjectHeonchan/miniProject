package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserInfoDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	boolean check = false;

	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "campus_d_5_0115";
			String password = "smhrd5";

			// 2. 사용할 계정 선택, db 연결 객체(Connection) 생성
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 사용한 객체들 반환(종료)
	public void close() {
		try {
			if (rs != null) {
				rs.close(); // selectStds(), selectOneStd()에서만 사용하는 객체
							// ResultSet 객체가 생성되었을때만 호출 가능한 메서드
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원가입 기능
	public boolean insertUser(String id, String password) {

		try {
			// jdbc 드라이버 불러오기
			connect();

			String sql = "INSERT INTO USER_INFO VALUES(?, ?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, password);
			pst.setInt(3, 0);

			int cnt = pst.executeUpdate();

			if (cnt > 0) {// 추가 성공
				check = true;
			} else { // 추가 실패
				check = false;
			}

		} catch (Exception e) {
			System.out.println("중복된 아이디거나 잘못된 형식입니다!");
		} finally {
			close();
		}
		return check;
	}// end of insertUser

	// 로그인 기능
	public boolean login(String id, String password) {
		boolean check1 = false;

		try {
			// jdbc 드라이버 불러오기
			connect();

			String sql = "SELECT ID, PASSWORD FROM USER_INFO";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				String get_id = rs.getString("ID");
				String get_password = rs.getString("PASSWORD");
				
				// 입력받은 id와 데이터베이스 id와 password 비교
				if (get_id.equals(id) && get_password.equals(password)) {
					check1 = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return check1;
	}// end of login

	public void updateScore(int score, String id) {

		try {
			connect();

			String sql = "UPDATE USER_INFO SET SCORE = ? WHERE ID = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, score);
			pst.setString(2, id);

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}


	// 데이터베이스에 점수가져오는 기능
	public int getScore(String id) {
		int score = 0;
		try {
			connect();

			String sql = "SELECT ID, SCORE FROM USER_INFO WHERE ID = ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				String get_id = rs.getString("ID"); // 현재 커서가 가르키고 있는 행의 첫번째 컬럼값을 읽어오겠다!
				int get_score = rs.getInt("SCORE"); // 컬럼이름과 일치하게 작성

				// 입력받은 id와 데이터베이스 id와 password 비교
				if (get_id.equals(id)) {
					score = get_score;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// rank_분석
	public ArrayList<UserInfoVO> rank() {
		ArrayList<UserInfoVO> al = new ArrayList<UserInfoVO>();
		try {

			connect();

			String sql = "SELECT ID, SCORE FROM USER_INFO ORDER BY SCORE DESC";

			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("ID"); 
				int score = rs.getInt("SCORE");
				
				//위에서 읽어온 값들로 초기화시켜 생성한 StudentVO 객체의 참조값을
				//ArrayList에 추가
				al.add(new UserInfoVO(id, score));
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return al;
	}

}// end of class
