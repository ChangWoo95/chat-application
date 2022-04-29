package me.changwoo.authserver.repository;

import me.changwoo.authserver.dto.FriendsDto;
import me.changwoo.authserver.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryCustom {

    @Query(value = "select new me.changwoo.authserver.dto.FriendsDto(m.member_id, m.nick_name)\n"
                    + "from member m\n"
                    + "join (select * from friends where member_id = :id) f on m.member_id = f.follower_id"
            , nativeQuery = true)
    List<FriendsDto> findFriendsById(String id);
}
