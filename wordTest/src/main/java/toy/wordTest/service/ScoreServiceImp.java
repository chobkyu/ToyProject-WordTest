package toy.wordTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toy.wordTest.DAO.ScoreDao;
import toy.wordTest.app.ScoreVO;

@Service("ScoreServie")
public class ScoreServiceImp implements ScoreService{
	@Autowired
	private ScoreDao sDao;
	
	@Override
	public void insertScore(ScoreVO sVO) {
		sDao.insertScore(sVO);
	}
	
	@Override
	public List<ScoreVO> selectScore(){
		return sDao.selectScore();
	}

}
