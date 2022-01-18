package Model;

public class UserInfoVO {

	// 필드 : 아이디, 비밀번호, 점수
	private String id;
	private String password;
	private int score;

	// 생성자
	public UserInfoVO() {}
	public UserInfoVO(String id, String password) {
		this.id = id;
		this.password = password;
		this.score = 0;
	}
	public UserInfoVO(String id, int score) {
		this.id = id;
		this.score = score;
	}

	// 메소드
	public String getID() {
		return id;
	}

	public String getPASSWORD() {
		return password;
	}

	public int getSCORE() {
		return score;
	}

}
