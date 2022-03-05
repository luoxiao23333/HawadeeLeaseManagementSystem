package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.dao.CommentBoardDao;
import com.group5.hawadeeleasemanagementsystem.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CommentBoardController {
    @Autowired
    private CommentBoardDao commentBoardDao;
    private void updateCommentBoard(ModelAndView mv) {
        List<Comment> commentList = commentBoardDao.getComments();

        mv.addObject("commentList", commentList);
    }

    @RequestMapping(value = "/comment/commentManagement")
    public ModelAndView commentManagement(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("/comment/commentManagement");
        this.updateCommentBoard(mv);
        return mv;
    }

    @RequestMapping(value = "/comment/newComment")
    public ModelAndView newComment(@RequestParam(name = "nickname") String nickname,
                                    @RequestParam(name = "content") String commentContent,
                                    HttpSession session) throws Exception {
        Comment comment = new Comment();

        comment.setNickname(nickname);
        comment.setContent(commentContent);
        commentBoardDao.addComment(comment);
        ModelAndView mv = new ModelAndView("/comment/commentManagement");

        this.updateCommentBoard(mv);

        return mv;
    }
}
