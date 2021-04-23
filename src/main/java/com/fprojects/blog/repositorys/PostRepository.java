package com.fprojects.blog.repositorys;

import com.fprojects.blog.entitys.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {
}
