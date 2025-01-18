package jblog.controller;

import jblog.security.Auth;
import jblog.service.*;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Controller
@RequestMapping("/{blogId:(?!assets).*}")
// URL에서 id 값을 매핑하려고 할 때,
// /assets/images/pic.jpg와 같은 URL은 정적 리소스를 제공하는 컨트롤러로 가도록 하고, 동적 컨텐츠와 혼동되지 않도록 처리
public class BlogController {

    private static BlogService blogService;
    private static UserService userService;
    private static CategoryService categoryService;
    private static PostService postService;
    private static FileUploadService fileUploadService;


    public BlogController(BlogService blogService, UserService userService, CategoryService categoryService, PostService postService, FileUploadService fileUploadService) {
        this.blogService = blogService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.postService = postService;
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping({"", "/{path1}", "/{path1}/{path2}"})
    public String main(
            @PathVariable("blogId") String id,
            @PathVariable("path1") Optional<Long> path1,
            @PathVariable("path2") Optional<Long> path2,
            Model model) {

        System.out.println("블로그 메인 화면");
        Long categoryId = 0L;
        Long postId = 0L;

        if (path2.isPresent()) {
            categoryId = path1.get();
            postId = path2.get();
        } else if (path1.isPresent()) {
            categoryId = path1.get();
        }

        // 서비스에서~
        // categoryId == 0L  -> default categoryId 결정
        // postId == 0L  -> default postId 결정

        // 1. 해당 이름을 가진 유저가 존재하지 않는 경우
        BlogVo blogVo = blogService.getBlog(id);
        if (blogVo == null) {
            return "redirect:/";
        }
        model.addAttribute("blog", blogVo);

        // 2. 해당 유저의 카테고리가 아닌 경우
        model.addAttribute("map", blogService.getContents(id, categoryId, postId));

        return "blog/main";
    }

    // @Auth 추천
    @Auth(role="ADMIN")
    @GetMapping("/admin")
    public String adminDefault(@PathVariable("blogId") String id, Model model) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        BlogVo blog = blogService.getBlog(id);
        System.out.println("blog: " + blog);

        model.addAttribute("blog", blog);
        System.out.println("ADMIN 페이지");
        return "blog/admin-default";
    }

    @Auth(role="ADMIN")
    @PostMapping("/admin/modify")
    public String modifyBlogInfo(@PathVariable("blogId") String id, BlogVo blogVo, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) { // null이 아닌 경우에만 프로필 update
            blogVo.setProfile(fileUploadService.restore(file));
        }
        blogVo.setId(id);
        blogService.modify(blogVo);
        return "redirect:/" + id + "/admin";
    }

    @Auth(role="ADMIN")
    @GetMapping("/admin/category")
    public String adminCategory(@PathVariable("blogId") String id, Model model) {
        model.addAttribute("blog", blogService.getBlog(id));
        model.addAttribute("category", categoryService.getCountCategoryByUserId(id));
        return "blog/admin-category";
    }

    @Auth(role="ADMIN")
    @GetMapping("/admin/category/delete/{categoryId}")
    public String deleteCategory(@PathVariable("blogId") String id, @PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
        return "redirect:/" + id + "/admin/category";
    }

    @Auth(role="ADMIN")
    @PostMapping("/admin/category/write")
    public String createCategory(@PathVariable("blogId") String id, CategoryVo categoryVo) {
        categoryVo.setBlogId(id);
        categoryService.createCategory(categoryVo);
        return "redirect:/" + id + "/admin/category";
    }

    @Auth(role="ADMIN")
    @GetMapping("/admin/write")
    public String adminWrite(@PathVariable("blogId") String id, Model model) {
        model.addAttribute("blog", blogService.getBlog(id));
        model.addAttribute("category", categoryService.getCategoryList(id));
        return "blog/admin-write";
    }

    @Auth(role="ADMIN")
    @PostMapping("/admin/write")
    public String adminWrite(@PathVariable("blogId") String id, PostVo postVo) {
        postService.createPost(postVo);
        return "redirect:/" + id + "/" + postVo.getCategoryId();
    }
}
