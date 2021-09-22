package com.example.blogposts.controller;

import com.example.blogposts.exception.BlogNotFoundException;
import com.example.blogposts.model.Blog;
import com.example.blogposts.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    //Get All Blogs
    @GetMapping
    public List<Blog> getBlogs(){
        return this.blogRepository.findAll();
    }

    //Get a Specific Blog
    @GetMapping("/{id}")
    public Blog getSpecificBlog(@PathVariable (value = "id") long userId){
        return this.blogRepository.findById(userId).orElseThrow( ()->
                new BlogNotFoundException("Blog not found with the id " + userId));
    }

    //Create a New Blog
    @PostMapping
    public Blog createBlog(@RequestBody Blog blog){
       return this.blogRepository.save(blog);
    }

    //Update a Blog
    @PutMapping("/{id}")
    public Blog updateBlog(@RequestBody Blog blog, @PathVariable (value = "id") long userId){

        Blog existingBlog = this.blogRepository.findById(userId).orElseThrow( ()->
                new BlogNotFoundException("Blog not found with the id " + userId));

        existingBlog.setTitle(blog.getTitle());
        existingBlog.setDescription(blog.getDescription());

        return this.blogRepository.save(existingBlog);


    }

    //Delete a Blog
    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable (value = "id") long userId){

        Blog existingBlog = this.blogRepository.findById(userId).orElseThrow( ()->
                new BlogNotFoundException("Blog not found with the id " + userId));

        this.blogRepository.delete(existingBlog);

        return ResponseEntity.ok().build();

    }

}
