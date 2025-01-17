package jblog.repository;

import jblog.vo.BlogVo;
import jblog.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {

    private static SqlSession sqlSession;

    public BlogRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void insert(BlogVo blogVo) {
        sqlSession.insert("blog.insert", blogVo);
    }

    public BlogVo findById(String id) {
        return sqlSession.selectOne("blog.findById", id);
    }

    public void update(BlogVo blogVo) {
        sqlSession.update("blog.update", blogVo);
    }
}
