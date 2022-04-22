package me.changwoo.authserver.repository;

import me.changwoo.authserver.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryCustom {
}
