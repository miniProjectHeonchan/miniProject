package Model;

public class UserInfoVO {

	// 필드 : 아이디, 비밀번호, 점수
	private String id;
	private int password;
	private int score;

	// 생성자
	public UserInfoVO(String id, int password, int score) {
		super();
		this.id = id;
		this.password = password;
		this.score = score;
	}

	// 메소드
	public String getID() {
		return id;
	}

	public int getPASSWORD() {
		return password;
	}

	public int getSCORE() {
		return score;
	}

}
