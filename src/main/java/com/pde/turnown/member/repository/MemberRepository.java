package com.pde.turnown.member.repository;

import com.pde.turnown.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByMemberID(String memberNo);
}
