package jblog.repository;

import jblog.valid.CheckIdValidator;
import jblog.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Map;

@Repository
public class UserRepository {

    private static SqlSession sqlSession;

    public UserRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public UserVo findByIdAndPassword(String id, String password) {
        return sqlSession.selectOne("user.findByIdAndPassword", Map.of("id", id, "password", password));
    }

    public void insert(UserVo vo) {
        sqlSession.insert("user.insert", vo);
    }

    public UserVo findById(String id) {
        return sqlSession.selectOne("user.findById", id);
    }
}
