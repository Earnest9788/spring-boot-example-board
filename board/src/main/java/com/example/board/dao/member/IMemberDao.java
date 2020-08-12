package com.example.board.dao.member;

import java.util.Optional;

import com.example.board.vo.member.MemberVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IMemberDao {
    
    public int createMember(MemberVo memberVo);
    public MemberVo findById(@Param("_id") String id);

}