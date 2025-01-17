package jblog.service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogService {

    private static BlogRepository blogRepository;
    private static CategoryService categoryService;
    private static PostService postService;

    public BlogService(BlogRepository blogRepository, CategoryService categoryService, PostService postService) {
        this.blogRepository = blogRepository;
        this.categoryService = categoryService;
        this.postService = postService;
    }

    public void createBlog(UserVo userVo) {
        BlogVo blogVo = new BlogVo(userVo.getId(), userVo.getName() + "님의 블로그", "/assets/upload-images/default.jpg");
        blogRepository.insert(blogVo);

        CategoryVo categoryVo = new CategoryVo(null, blogVo.getId(), "기본 카테고리", "기본 카테고리입니다.");
        categoryService.createCategory(categoryVo);

        PostVo postVo = new PostVo(null, userVo.getName() + "님의 블로그에 오신 것을 환영합니다.", "보고 싶은 게시글 및 카테고리를 선택해주세요.", null, categoryVo.getId());
        postService.createPost(postVo);
    }

    public BlogVo getBlog(String blogId) {
        return blogRepository.findById(blogId);
    }

    public Object getContents(String blogId, Long categoryId, Long postId) {
        Map<String, Object> contentsList = new HashMap<>();

        // 해당하는 카테고리가 없는 경우
        CategoryVo currentCategory = categoryService.getCategory(categoryId);
        System.out.println("currentCategory: " + currentCategory);
        if (currentCategory == null || !currentCategory.getBlogId().equals(blogId)) {
            // 해당하는 카테고리가 없거나, 현재 유저의 카테고리가 아닌 경우
            categoryId = categoryService.getLastCategoryByBlogId(blogId).getId();
        }

        // 카테고리 출력
        List<CategoryVo> categories = categoryService.getCategoryList(blogId);
        contentsList.put("category", categories);

        // 카테고리에 해당하는 글 출력
        List<PostVo> postList = postService.getPostList(blogId, categoryId);
        if (postList.isEmpty()) { // 카테고리에 해당되는 글이 아무것도 없는 경우
            contentsList.put("isEmptyPost", true); // "작성된 게시글이 없습니다." 보여주기
            return contentsList;
        }

        contentsList.put("postList", postList);
        contentsList.put("isEmptyPost", false);

        // categoryId에 해당하는 카테고리 내부에 postId 글이 없는 경우 -> categoryId의 최신글 보여주기
        PostVo post = postService.getPost(postId);
        if (post == null || !categoryId.equals(post.getCategoryId())) {
            post = postService.getLastPost(categoryId);
        }
        contentsList.put("post", post);

        return contentsList;
    }

    public void modify(BlogVo blogVo) {
        blogRepository.update(blogVo);
    }
}
