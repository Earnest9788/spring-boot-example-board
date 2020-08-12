package com.example.board.services.member;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.board.dao.member.IMemberDao;
import com.example.board.vo.member.MemberVo;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IMermberService {

    @Autowired
    private IMemberDao memberDao; 

    @Override
    public void regist(MemberVo memberVo) {

        String rawPassword = memberVo.getPassword();
        // String encodedPassword = passwordEncoder.encode(rawPassword);

        // memberVo.setPassword(encodedPassword);
        memberVo.setEnabled(true);
        memberVo.setRegiDate(getCurrentTime());
        memberVo.setRole("ROLE_USER");

        memberDao.createMember(memberVo);

    }

    @Override
    public MemberVo login(String id, String pw) {

        MemberVo byId = null;

        try {
            byId = memberDao.findById(id);

            if (byId == null) {
                throw new NotFoundException(id);
            }

            if (!pw.equals(byId.getPassword())) {
                byId = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return byId;

    }

    @Override
    public void logout(HttpServletRequest req) {
        
        HttpSession session = req.getSession();
        session.invalidate();

    }

    @Override
    public void setSession(HttpServletRequest req, MemberVo memberVo) {
        
        HttpSession session = req.getSession(true);
        session.setAttribute("memberId", memberVo.getId());
        session.setAttribute("memberName", memberVo.getName());
        session.setAttribute("memberRole", memberVo.getRole());

    }

    private Timestamp getCurrentTime() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

}