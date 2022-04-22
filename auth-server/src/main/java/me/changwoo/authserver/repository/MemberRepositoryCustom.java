package me.changwoo.authserver.repository;

import me.changwoo.authserver.dto.MemberDto;

import java.util.List;

public interface MemberRepositoryCustom {

//    public List<MemberDto> findAllByEmail(String email);
    public MemberDto findByEmail(String email);
}
