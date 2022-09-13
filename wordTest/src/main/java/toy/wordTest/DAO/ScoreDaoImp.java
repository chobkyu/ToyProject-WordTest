package toy.wordTest.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import toy.wordTest.app.ScoreVO;

@Repository
public class ScoreDaoImp implements ScoreDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="toy.wordTest.mapper.ScoreMapper";
	
	@Override
	public void insertScore(ScoreVO sVO) {
		sqlSession.insert(namespace+".insertScore",sVO);
	}
	
	@Override
	public List<ScoreVO> selectScore(){
		return sqlSession.selectList(namespace+".selectScore");
	}
}
