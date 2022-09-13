package toy.wordTest.service;

import java.util.List;

import toy.wordTest.app.ScoreVO;

public interface ScoreService {
	public void insertScore(ScoreVO sVO);
	public List<ScoreVO> selectScore();
}
