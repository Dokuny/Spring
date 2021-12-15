package com.dokuny.comunitysite.login.service;


import com.dokuny.comunitysite.login.domain.MemberLoginDTO;
import com.dokuny.comunitysite.member.domain.Member;
import com.dokuny.comunitysite.member.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginCheckService {

    private final MemberDAO memberDAO;

    public Member checkLogin(MemberLoginDTO memberLoginDTO) {
        Member findMember = memberDAO.findById(memberLoginDTO.getId());

        if (findMember != null) {
            if (findMember.getPw().equals(memberLoginDTO.getPw())) {
                return findMember;
            }
        }

        return null;
    }
}
