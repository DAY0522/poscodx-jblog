package jblog.service;

import jblog.repository.PostRepository;
import jblog.vo.PostVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private static PostRepository postRepository;

    public PostService(SqlSession sqlSession, PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(PostVo postVo) {
        postRepository.insert(postVo);
    }

    public List<PostVo> getPostList(String blogId, Long categoryId) {
        return postRepository.findByBlogIdAndCategoryId(blogId, categoryId);
    }

    public PostVo getPost(Long postId) {
        return postRepository.findById(postId);
    }

    public PostVo getLastPost(Long categoryId) {
        return postRepository.findLastPostByCategoryId(categoryId);
    }

    public void moveToDefaultCategoryByCategoryId(Long categoryId) {
        postRepository.updatePostByCategoryId(categoryId);
    }

}
