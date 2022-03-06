package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentBoardDao {
    List<Comment> getComments();
    void addComment(Comment comment);
    void removeComment(Integer commentID);
}
