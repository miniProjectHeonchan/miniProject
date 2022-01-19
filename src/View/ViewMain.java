package View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Controller.GameStartController;
import Model.UserInfoDAO;
import Model.UserInfoVO;
import Model.WordListDAO;
import Model.WordListVO;
import javazoom.jl.player.MP3Player;

public class ViewMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		UserInfoDAO userDAO = new UserInfoDAO();
		WordListDAO wordDAO = new WordListDAO();

		//컨트롤러 객체 생성
		GameStartController con = new GameStartController();
		
		// MP3Player 사용하기 위해 가장먼저해야할일!
		MP3Player mp3 = new MP3Player();

		// Music music1 = new Music("C:\\nope.mp3");

		System.out.println("　　　　  ∧＿∧　　　　 ");
		System.out.println("　 　　 (*･∀･*)　　　　 ");
		System.out.println("★*。。:ﾟ*〇☆〇*ﾟ:。。:*★");
		System.out.println("☆｡。*･:+*ﾟ　　 ﾟ*+:･*｡。☆");
		System.out.println("▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀ ");
		System.out.println("　     년  도  별　     ");
		System.out.println("　   문 제 맞 추 기　   ");
		System.out.println("       게   임       ");
		System.out.println("▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀");
		System.out.println();
		System.out.println("신규 유저는 회원가입을 진행 후 로그인해!!^^");

		while (true) {
			System.out.print("1번 회원가입 2번 로그인 3번 종료 번호 입력 >>>> ");
			int choice = sc.nextInt();

			if (choice == 1) { // 회원가입
				System.out.println("☆★☆★회원가입 진행 중☆★☆★");
				System.out.print("아이디 입력해!! ");
				String id = sc.next();
				System.out.print("비밀번호 입력해!! ");
				String pw = sc.next();

				boolean check = userDAO.insertUser(id, pw);

				if (check) {
					System.out.println("아이디 생성 성공!!");
				} else {
					System.out.println("아이디 생성 실패!!");
				}
				System.out.println();
			} else if (choice == 2) { // 로그인
				System.out.println("☆★☆★로그인 중☆★☆★");
				System.out.print("아이디 입력해!! ");
				String id = sc.next();
				System.out.print("비밀번호 입력해!! ");
				String password = sc.next();

				boolean check = userDAO.login(id, password);
				System.out.println();

				if (check) {
					System.out.println("로그인 성공!!");
					mp3.play("C:\\nope.mp3");
					System.out.println();

					while (true) {
						System.out.println("☆★☆★☆★1번 게임하기☆★☆★☆★");
						System.out.println("☆★☆★☆★2번 누적결과☆★☆★☆★");
						System.out.println("☆★☆★☆★3번 랭킹확인☆★☆★☆★");
						System.out.println("☆★☆★☆★4번 로그아웃☆★☆★☆★");
						System.out.print("번호 선택 >>>>>>>>>>>>>>>>>>>>> ");
						int num = sc.nextInt();

						if (num == 1) {// 게임하기
							System.out.print("1번 2000년대, 2번 2010년대, 3번 2020년대 선택 >>> ");
							int yearNum = sc.nextInt();
							
							System.out.println("게임을 시작!!!!");

							if (yearNum == 1) {// 2000년대
								//게임시작호출
								con.start(2000, id);
							} else if (yearNum == 2) {// 2010년대
								//게임시작호출
								// 2010년대
								con.start(2010, id);

							} else if (yearNum == 3) {// 2020년대
								//게임시작호출
								// 2020년대
								con.start(2020, id);

							} else {
								System.out.println("잘못 입력!!");
							}

						} else if (num == 2) {// 누적결과보기
							int totalScore = userDAO.getScore(id);
							System.out.println(totalScore + "점 입니다.!");

							if (totalScore > 80) {
								System.out.println("⊂_ ヽ、");
								System.out.println("　 ＼＼ Λ＿Λ");
								System.out.println("　　 ＼( ‘ㅅ’ ) 두둠칫");
								System.out.println("　　　 >　⌒ヽ");
								System.out.println("　　　/ 　 へ＼");
								System.out.println("　　 /　　/　＼＼");
								System.out.println("　　 ﾚ　ノ　　 ヽ_つ");
								System.out.println("　　/　/두둠칫");
								System.out.println("　 /　/|");
								System.out.println("　(　(ヽ");
								System.out.println("　|　|、＼");
								System.out.println("　| |　　) /");
								System.out.println("`ノ )　　Lﾉ");
								System.out.println("");
								System.out.println("시대를 아우르는 당신!! 유행어의 마스터 이시군요!");

							} else if (totalScore > 60) {
								System.out.println("      ∧＿∧");
								System.out.println("   　（´・ω・)つ＿  ∧");
								System.out.println("     （つ　 / (・ω・｡)");
								System.out.println("    　 しーＪ　(nnノ)");
								System.out.println("");
								System.out.println(" 그래도 노력하는 당신..쫌만 더 열심히 하세요!");

							} else if (totalScore > 40) {

								System.out.println(" ༼ ºل͟º ༼ ºل͟º ༼ ºل͟º ༽ ºل͟º ༽ ºل͟º ༽");
								System.out.println("");
								System.out.println("조금 더 유행어 공부를 해보시는게 어떤가요?");

							} else {
								System.out.println(" ┏━━━━━┓ ");
								System.out.println("┃  　 　 ┃ ");
								System.out.println("┃　┏ ━┓　┃");
								System.out.println("┗━┛　 ┃　┃");
								System.out.println("　  ┏━┛　┃");
								System.out.println("　  ┃ ┏━┛");
								System.out.println("　  ┗━┛ ");
								System.out.println("　　┏━┓ ");
								System.out.println("　　┃ ┃ ");
								System.out.println("　　┗━┛  ");
								System.out.println("　　　　〇 ");
								System.out.println("　　　　ｏ  ");
								System.out.println("　　　　　(・д・) ");
								System.out.println("");
								System.out.println("당신은 문제를 읽어보긴하셨나요?");
							}
							System.out.println();
						} else if (num == 3) {// 랭킹확인
							// 1.모든 사용자 출력 점수에 따라 정렬 후 출력
							System.out.println("=========랭킹 확인=========");

							ArrayList<UserInfoVO> rank = userDAO.rank();
							for (int i = 0; i < 10; i++) {
								System.out.print((i + 1) + "위 아이디 : " + rank.get(i).getID());
								System.out.print("\t\t점수 : " + rank.get(i).getSCORE());
								System.out.println();
							}
							System.out.println();

						} else if (num == 4) {// 로그아웃
							System.out.println("로그아웃!!");
							break;
						} else {
							System.out.println("잘못입력!!");
						}
					}

				} else {
					System.out.println("로그인 실패!!");
				}

			} else if (choice == 3) { // 종료
				System.out.println("종료!!");
				break;
			} else { // 잘못입력
				System.out.println("잘못 입력!!");
			}

		} // end of while
	}// end of main
}// end of class
