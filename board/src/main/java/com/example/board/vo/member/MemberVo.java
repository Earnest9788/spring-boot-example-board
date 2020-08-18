package com.example.board.vo.member;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberVo {

    private int keyIdx;

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String phone;

    private String role;
    private Timestamp regiDate;
    private boolean enabled;

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {

    //     ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
    //     auth.add(new SimpleGrantedAuthority(role));
    //     return auth;
    // }

    // @Override
    // public String getPassword() {
    //     // TODO Auto-generated method stub
    //     return password;
    // }

    // @Override
    // public String getUsername() {
    //     // TODO Auto-generated method stub
    //     return id;
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    //     // TODO Auto-generated method stub
    //     return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     // TODO Auto-generated method stub
    //     return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     // TODO Auto-generated method stub
    //     return true;
    // }

    // @Override
    // public boolean isEnabled() {
    //     // TODO Auto-generated method stub
    //     return enabled;
    // }

}