package toy.wordTest.DAO;

import java.util.List;

import toy.wordTest.app.WordVO;

public interface wordTestDao {
	public int lastLevel() throws Exception;
	public List<WordVO> exam(int seq);
}
