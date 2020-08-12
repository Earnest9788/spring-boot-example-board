package com.example.board.dao.member;

import com.example.board.vo.member.MemberVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMemberDao {
    
    public int create(MemberVo memberVo);

}