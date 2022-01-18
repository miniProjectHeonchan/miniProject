package Model;

public class WordListVO {
	private String word;
	private String mean;
	private int year;
	private String hint1;
	private String hint2;

	public WordListVO(String word, String mean, int year, String hint1, String hint2) {
		this.word = word;
		this.mean = mean;
		this.year = year;
		this.hint1 = hint1;
		this.hint2 = hint2;
	}

	public String getWord() {
		return word;
	}

	public String getMean() {
		return mean;
	}

	public int getYear() {
		return year;
	}

	public String getHint1() {
		return hint1;
	}

	public String getHint2() {
		return hint2;
	}


}
