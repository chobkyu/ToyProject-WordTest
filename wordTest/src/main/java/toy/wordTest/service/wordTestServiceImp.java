package toy.wordTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toy.wordTest.DAO.wordTestDao;

@Service("wordTestService")
public class wordTestServiceImp implements wordTestService {
	
	@Autowired
	private wordTestDao wDao;
	@Override
	public int lastLevel() throws Exception{
		return wDao.lastLevel();
	}
}
