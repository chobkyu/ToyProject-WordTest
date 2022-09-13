package toy.wordTest.DAO;

import java.util.List;

import toy.wordTest.app.ScoreVO;

public interface ScoreDao {
	public void insertScore(ScoreVO sVO);
	public List<ScoreVO> selectScore();
}
