package com.example.board.services.member;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.example.board.dao.member.IMemberDao;
import com.example.board.vo.member.MemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IMermberService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @Autowired
    private IMemberDao memberDao;

    private Timestamp getCurrentTime() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public void regist(MemberVo memberVo) {

        String rawPassword = memberVo.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        memberVo.setPassword(encodedPassword);
        memberVo.setEnabled(true);
        memberVo.setRegiDate(getCurrentTime());
        memberVo.setRole("ROLE_USER");

        System.out.println("[----- IMemberService.java -----]");
        System.out.println(memberVo.getAuthorities());
        System.out.println(memberVo.getPassword());
        System.out.println(memberVo.getRegiDate());
        System.out.println("---------------------------------");

        memberDao.create(memberVo);

    }

    @Override
    public void login() {
        // TODO Auto-generated method stub

    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub

    }

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

}