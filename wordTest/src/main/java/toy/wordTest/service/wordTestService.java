package toy.wordTest.service;

import java.util.List;

import toy.wordTest.app.WordVO;

public interface wordTestService {
	public int lastLevel() throws Exception;
	public List<WordVO> exam(int seq);
}
