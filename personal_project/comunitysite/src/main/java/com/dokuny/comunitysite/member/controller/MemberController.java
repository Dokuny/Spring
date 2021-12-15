package com.dokuny.comunitysite.member.controller;

import com.dokuny.comunitysite.member.domain.Member;
import com.dokuny.comunitysite.member.domain.MemberForm;
import com.dokuny.comunitysite.member.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberDAO memberDAO;

    @GetMapping
    public String mainPage(){
        return "member/main";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("members",memberDAO.findAll());
        return "member/list";
    }

    @GetMapping("/enroll")
    public String enrollForm(Model model) {
        model.addAttribute("member",new Member());
        return "member/enroll";
    }

    @PostMapping("/enroll")
    public String enroll(@ModelAttribute("member") @Validated MemberForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "member/enroll";
        }
        Member member = new Member(form.getId(), form.getPw(), form.getName(), form.getAge(), form.getGender(), form.getPhone());
        memberDAO.save(member);
        return "redirect:/members";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("members",memberDAO.findAll());
        return "member/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(required = false) List<String> checkedValue) {
        if (checkedValue == null) {
            return "redirect:/members/delete";
        }
        for (String id : checkedValue) {
            memberDAO.delete(id);
        }
        return "redirect:/members/delete";
    }

    @GetMapping("/edit")
    public String editList(Model model) {
        model.addAttribute("members",memberDAO.findAll());
        return "member/edit";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id,Model model) {
        Member member = memberDAO.findById(id);
        model.addAttribute("member",member);
        return "member/editMember";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute("member") @Validated MemberForm form,BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "member/editMember";
        }
        Member member = new Member(form.getId(), form.getPw(), form.getName(), form.getAge(), form.getGender(), form.getPhone());
        memberDAO.edit(member);
        return "redirect:/members";
    }

    @GetMapping("/search")
    public String searchForm() {
       return "member/search";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false) String id, Model model) {
        if (id.isEmpty()) {
            return "redirect:/members/search";
        }
        Member member = memberDAO.findById(id);
        if (member == null) {
            return "redirect:/members/search";
        }
        model.addAttribute("member",member);
        model.addAttribute("inputStr",id);
        return "member/search";
    }
}
