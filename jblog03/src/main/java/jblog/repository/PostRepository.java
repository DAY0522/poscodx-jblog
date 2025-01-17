package jblog.repository;

import jblog.vo.PostVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {

    private static SqlSession sqlSession;

    public PostRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void insert(PostVo postVo) {
        sqlSession.insert("post.insert", postVo);
    }
    public List<PostVo> findByBlogIdAndCategoryId(String blogId, Long categoryId) {
        return sqlSession.selectList("post.findByBlogIdAndCategoryId", Map.of("blogId", blogId, "categoryId", categoryId));
    }

    public PostVo findById(Long id) {
        return sqlSession.selectOne("post.findById", id);
    }

    public PostVo findLastPostByCategoryId(Long categoryId) {
        return sqlSession.selectOne("post.findLastPostByCategoryId", categoryId);
    }

    public void updatePostByCategoryId(Long categoryId) { // 기본 카테고리로 옮기기
        sqlSession.delete("post.updatePostByCategoryId", categoryId);
    }
}
