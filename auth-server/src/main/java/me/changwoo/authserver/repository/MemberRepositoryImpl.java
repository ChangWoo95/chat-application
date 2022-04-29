package me.changwoo.authserver.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.changwoo.authserver.dto.MemberDto;
import me.changwoo.authserver.dto.QMemberDto;
import me.changwoo.authserver.repository.entity.QMember;

import java.util.List;


@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory factory;

    // 로그인에서 validation하겠다고 잘못 생각
    // => 회원가입에서 validation을 잡는것으로 변경 - 2022.04.23
//    @Override
//    public List<MemberDto> findAllByEmail(String email) {
//        QMember member = QMember.member;
//        return factory
//                .select(Projections.constructor(MemberDto.class, member.id, member.email, member.password, member.nickName))
//                .from(member)
//                .where(member.email.eq(email))
//                .fetch();
//    }

    @Override
    public MemberDto findByEmail(String email) {
        QMember member = QMember.member;

        return factory
                .select(new QMemberDto(member.id, member.email, member.password, member.nickName, member.phoneNumber))
                .from(member)
                .where(member.email.eq(email))
                .fetchOne();
    }
}
