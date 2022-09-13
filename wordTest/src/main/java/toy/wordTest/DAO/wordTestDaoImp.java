package toy.wordTest.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import toy.wordTest.app.WordVO;

@Repository
public class wordTestDaoImp implements wordTestDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="toy.wordTest.mapper.wordMapper";
	
	@Override
	public int lastLevel() throws Exception{
		return sqlSession.selectOne(namespace+".lastLevel");
	}
	
	@Override
	public List<WordVO> exam(int seq) {
		return sqlSession.selectList(namespace+".exam",seq);
	}
}
