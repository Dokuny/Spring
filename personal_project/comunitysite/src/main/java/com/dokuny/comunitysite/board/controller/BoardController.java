package com.dokuny.comunitysite.board.controller;

import com.dokuny.comunitysite.board.domain.PostDTO;
import com.dokuny.comunitysite.board.domain.PostDb;
import com.dokuny.comunitysite.board.domain.Reply;
import com.dokuny.comunitysite.board.repository.BoardDAO;
import com.dokuny.comunitysite.login.controller.Session_Const;
import com.dokuny.comunitysite.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private BoardDAO dao;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("lists", dao.findAll());
        return "board/list";
    }

    @GetMapping("/post")
    public String postForm() {
        return "board/post";
    }

    @PostMapping("/post")
    public String post(PostDb post, @SessionAttribute(name = Session_Const.LOGIN_MEMBER, required = false) Member member) {
        PostDTO postDTO = new PostDTO(post.getTitle(), member.getId(), Timestamp.valueOf(LocalDateTime.now()), 0L, post.getText());
        dao.save(postDTO);
        return "redirect:/board";
    }

    @GetMapping("/{id}")
    public String innerContent(@PathVariable Long id,@SessionAttribute(name = Session_Const.LOGIN_MEMBER, required = false) Member member,Model model,HttpServletRequest request){
        PostDTO post = dao.findById(id);
        dao.plusViews(post);
       if(post.getUser().equals(member.getId())){
           model.addAttribute("isYours",true);
       }
        model.addAttribute("post",post);
        model.addAttribute("replies",dao.findReplyAll(id));
        model.addAttribute("uri",request.getRequestURI());
        return "board/content";
    }

    @GetMapping("/{id}/delete")
    public String deleteContent(@PathVariable Long id,@SessionAttribute(name = Session_Const.LOGIN_MEMBER, required = false) Member member){
        PostDTO findPost = dao.findById(id);
        if (findPost.getUser().equals(member.getId())) {
            dao.delete(id);
        }
        return "redirect:/board";
    }

    @PostMapping("/{id}/reply")
    public String saveReply(@PathVariable Long id,@ModelAttribute Reply reply){

        if (reply.getId()==0 || reply.getReplyText().equals("") || reply.getReplyText().equals(null)) {
            return "redirect:/board/{id}";
        }

        dao.saveReply(reply);
        return "redirect:/board/{id}";
    }
}
