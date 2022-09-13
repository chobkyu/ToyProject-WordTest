package toy.wordTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toy.wordTest.DAO.wordTestDao;
import toy.wordTest.app.WordVO;

@Service("wordTestService")
public class wordTestServiceImp implements wordTestService {
	
	@Autowired
	private wordTestDao wDao;
	@Override
	public int lastLevel() throws Exception{
		return wDao.lastLevel();
	}
	
	@Override
	public List<WordVO> exam(int seq) {
		return wDao.exam(seq);
	}
}
