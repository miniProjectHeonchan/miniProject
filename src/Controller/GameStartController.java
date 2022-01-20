package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Model.UserInfoDAO;
import Model.WordListDAO;
import Model.WordListVO;
import javazoom.jl.player.MP3Player;

public class GameStartController {
	
	//게임 시작 메서드
	public static void start(int yearNum, String id) {
		Scanner sc = new Scanner(System.in);
		UserInfoDAO userDAO = new UserInfoDAO();
		WordListDAO wordDAO = new WordListDAO();
		MP3Player mp3 = new MP3Player();

		// 2000년대

		ArrayList<WordListVO> vo = wordDAO.wordList(yearNum);
		int score = 0; // 문제 점수

		// vo객체(데이터베이스의 단어뜻) 인덱스값 랜덤을 정렬시킴
		Collections.shuffle(vo);

		System.out.println("　　　　  ∧_∧");
		System.out.println("　　　　 (`･ω･´ ) 三");
		System.out.println("　　　　 O┳〇 )");
		System.out.println("　　　　 ◎し◎- 三 ");
		System.out.println("　　　　 " + yearNum + "년대로 가는 중...");
		System.out.println("");
		
		// 게임시작시 효과음 재생
		mp3.play("C:\\게임시작.mp3");
		for (int i = 0; i < 5; i++) {

			// 문제출력
			System.out.println("   ════════════════════•°• ⚠ •°•════════════════════");
			System.out.println("　　　　　Q. " + vo.get(i).getMean());
			System.out.println("   ════════════════════•°• ⚠ •°•════════════════════");

			// 힌트점수누적
			int hintScore = 0;
			// 힌트 본 횟수
			int hintCnt = 0;

			while (true) {
				// 힌트보기 선택지 주기? 1번 정답입력 2번 힌트보기
				System.out.println();
				System.out.println("　　　　        ①.정답입력       ");
				System.out.println("　　　　        ②.힌트보기       ");
                System.out.println();
				System.out.print("　　　　  　번호선택 >>>>  ");


				int num3 = sc.nextInt();
				
				if (num3 == 1) {
					// 답 입력
					System.out.print("　　　　  　정답입력 >>>> ");
					String str = sc.next();

					if (vo.get(i).getWord().equals(str)) {

						// 점수추가
						score += 10 - hintScore;
						System.out.println();
						System.out.println("　　　　　　  　 .^   ^");
						System.out.println("　　　　　　  　 \\(>~<)/");
						System.out.println("　　　　　　  　  ( . ) ");
						System.out.println("　　　　　　  　  /    \\_");
						System.out.println("　　　　　　  　    정답!");
						System.out.println();

						// 정답 맞췄을때 효과음 출력
						mp3.play("C:\\정답.mp3");
						System.out.println();
						break;

					} else {
						System.out.println();
						System.out.println("　　　　　　  　╭┈ ↷");
						System.out.println("　　　　　　  　│     정답은");
						System.out.println("　　　　　　  　│    ┆ " + vo.get(i).getWord() + "입니다.");
						System.out.println("　　　　　　  　╰──────────────────────────────────────⠀⠀");

						// 정답 틀렸을때 효과음 출력
						mp3.play("C:\\오답.mp3");
						System.out.println();
						break;
					}

				} else if (num3 == 2) {
					if (hintCnt == 0) {
						// 힌트 1번 출력
						System.out.println();
						System.out.println("　　　　　　  　     첫번째 힌트 입니다!!");
						System.out.println("　　　　　　  　|￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
						System.out.println("\t\t\t" + vo.get(i).getHint1());
						System.out.println("　　　　　　  　|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
						System.out.println("　　　　　　  　　　     ᕱ ᕱ ||");
						System.out.println("　　　　　　  　　      ( ･ω･ ||");
						System.out.println("　　　　　　  　       /　つΦ");
						hintCnt++;
						hintScore += 3;
					} else if (hintCnt == 1) {
						System.out.println();
						System.out.println("　　　　　　  　     두번째 힌트 입니다!!");
						System.out.println("　　　　　　  　 모든힌트를 다 보셨습니다!!");
						System.out.println("　　　　　　  　|￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
						System.out.println("\t\t\t" + vo.get(i).getHint2());
						System.out.println("　　　　　　  　|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
						System.out.println("　　　　　　  　　　     ᕱ ᕱ ||");
						System.out.println("　　　　　　  　　      ( ･ω･ ||");
						System.out.println("　　　　　　  　       /　つΦ");
						hintCnt++;
						hintScore += 2;
					} else {
						System.out.println();
						System.out.println("　　　　　　  　힌트를 다보셨습니다 ┐(￣ヘ￣)┌");
						System.out.println();
					}
				} else {
					System.out.println();
					System.out.println("　　　　  ☆★잘못입력 1 또는 2로 입력☆★");
					System.out.println();
				}
			}
		} // end of for문
		int totalScore = userDAO.getScore(id);
		userDAO.updateScore(totalScore + score, id);
		System.out.println("　　　　    /)⋈/)");
		System.out.println("　　　　   (｡•ㅅ•｡)♡");
		System.out.println("　　　　  ┏--∪-∪━━━━━┓");
		System.out.println("　　　　  " + score + "점 입니다");
		System.out.println("　　　　   ♡   　　　　 *.。♡");
		System.out.println("　　　　  ┗-━━━━━━━┛");

		// 문제가 끝나고 효과음 출력
		mp3.play("C:\\문제끝.mp3");
		System.out.println();
	}

	
}
