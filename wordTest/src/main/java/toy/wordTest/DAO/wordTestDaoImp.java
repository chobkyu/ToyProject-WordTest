package toy.wordTest.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class wordTestDaoImp implements wordTestDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="toy.wordTest.mapper.wordMapper";
	
	@Override
	public int lastLevel() throws Exception{
		return sqlSession.selectOne(namespace+".lastLevel");
	}
}
