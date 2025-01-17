package jblog.service;

import jblog.repository.UserRepository;
import jblog.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static UserRepository userRepository;
    private static BlogService blogService;

    public UserService(UserRepository userRepository, BlogService blogService) {
        this.userRepository = userRepository;
        this.blogService = blogService;
    }

    @Transactional
    public void join(UserVo vo) {
        userRepository.insert(vo); // 회원가입
        blogService.createBlog(vo);
    }

    public UserVo getUser(String id) {
        return userRepository.findById(id);
    }

    public UserVo getUser(String id, String password) {
        return userRepository.findByIdAndPassword(id, password);
    }
}
