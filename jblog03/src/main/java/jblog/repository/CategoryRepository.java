package jblog.repository;

import jblog.vo.CategoryVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CategoryRepository {

    private static SqlSession sqlSession;

    public CategoryRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void insert(CategoryVo categoryVo) {
        sqlSession.insert("category.insert", categoryVo);
    }

    public List<CategoryVo> findAllByBlogId(String blogId) {
        return sqlSession.selectList("category.findAllByBlogId", blogId);
    }

    public CategoryVo findByCategoryId(Long id) {
        return sqlSession.selectOne("category.findByCategoryId", id);
    }

    public CategoryVo findLastCategoryByBlogId(String blogId) {
        return sqlSession.selectOne("category.findLastCategoryByBlogId", blogId);
    }

    public List<CategoryVo> findCountCategoryByUserId(String userId) {
        return sqlSession.selectList("category.findCountCategoryByUserId", userId);
    }

    public void deleteById(Long id) {
        sqlSession.delete("category.deleteById", id);
    }
}
