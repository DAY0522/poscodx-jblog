package jblog.service;

import jblog.repository.CategoryRepository;
import jblog.repository.PostRepository;
import jblog.vo.CategoryVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private static CategoryRepository categoryRepository;
    private static PostService postService;

    public CategoryService(CategoryRepository categoryRepository, PostService postService) {
        this.categoryRepository = categoryRepository;
        this.postService = postService;
    }

    public void createCategory(CategoryVo categoryVo) {
        categoryRepository.insert(categoryVo);
    }

    public List<CategoryVo> getCategoryList(String blogId) {
        return categoryRepository.findAllByBlogId(blogId);
    }

    public CategoryVo getCategory(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    public CategoryVo getLastCategoryByBlogId(String blogId) {
        return categoryRepository.findLastCategoryByBlogId(blogId);
    }

    public List<CategoryVo> getCountCategoryByUserId(String userId) {
        return categoryRepository.findCountCategoryByUserId(userId);
    }

    public void delete(Long id) {
        postService.moveToDefaultCategoryByCategoryId(id); // 기본 카테고리로 옮기기
        categoryRepository.deleteById(id);
    }
}
