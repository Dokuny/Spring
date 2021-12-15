package com.dokuny.comunitysite.member.repository;

import com.dokuny.comunitysite.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MemberDAO {

    private final JdbcTemplate jdbcTemplate;


    public void save(@ModelAttribute Member member){
        String sql ="insert into members(id,pw,name,age,gender,phone) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql,member.getId(),member.getPw(),member.getName(),member.getAge(),member.getGender(),member.getPhone());
    }

    public List<Member> findAll(){
        List<Member> list = jdbcTemplate.query("select * from members", new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(
                        rs.getString("id"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone")
                );
                return member;
            }
        });
        return list;
    }

    public void delete(String id){
        String sql = "delete from members where id = (?)";
        jdbcTemplate.update(sql,id);
    }

    public void edit(Member member){
        String sql = "update members set pw = ? ,name = ?, age = ?, gender = ?, phone = ? where id =?";
        jdbcTemplate.update(sql,member.getPw(),member.getName(),member.getAge(),member.getGender(),member.getPhone(),member.getId());
    }

    public Member findById(String id){
        String sql = "select * from members where id = ?";
        try{
        Member member = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Member member1 = new Member(
                    rs.getString("id"),
                    rs.getString("pw"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("phone")
            );
            return member1;
        },id);
            return member;
        }catch (Exception e){
            return null;
        }
    }
}
