package com.dokuny.comunitysite.board.repository;

import com.dokuny.comunitysite.board.domain.PostDTO;
import com.dokuny.comunitysite.board.domain.Reply;
import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {

    private final JdbcTemplate jdbcTemplate;

    public void save(PostDTO postDTO){
        String sql ="insert into board_post(title,user,date,views,text) values (?,?,?,?,?)";
        jdbcTemplate.update(sql,postDTO.getTitle(),postDTO.getUser(), postDTO.getDate(),postDTO.getViews(),postDTO.getText());
    }

    public List<PostDTO> findAll(){
        List<PostDTO> list = jdbcTemplate.query("select * from board_post", (rs, rowNum) -> {
            PostDTO postDTO = new PostDTO(
                    rs.getString("title"),
                    rs.getString("user"),
                    rs.getDate("date"),
                    rs.getLong("views"),
                    rs.getString("text")
            );
            postDTO.setId( rs.getLong("id"));
            return postDTO;
        });
        return list;
    }

    public PostDTO findById(Long id){
        String sql="select * from board_post where id = "+id;
        PostDTO postDTO1 = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            PostDTO postDTO = new PostDTO(
                    rs.getString("title"),
                    rs.getString("user"),
                    rs.getDate("date"),
                    rs.getLong("views"),
                    rs.getString("text")
            );
            postDTO.setId(rs.getLong("id"));
            return postDTO;
        });
        return postDTO1;
    }

    public void plusViews(PostDTO postDTO){
        String sql = "update board_post set views = ? where id =?";
        jdbcTemplate.update(sql,postDTO.getViews()+1,postDTO.getId());
    }

    public void saveReply(Reply reply){
        String sql ="insert into reply(id,user,replyText) values (?,?,?)";
        jdbcTemplate.update(sql,reply.getId(),reply.getUser(),reply.getReplyText());
    }
    public List<Reply> findReplyAll(Long id){
        List<Reply> list = jdbcTemplate.query("select * from reply where id=?", (rs, rowNum) -> {
            Reply reply= new Reply(
                    rs.getInt("id"),
                    rs.getString("user"),
                    rs.getString("replyText")
            );
            return reply;
        },id);
        return list;
    }

    public void delete(Long id) {
        String sql ="delete from board_post where id=?";
        jdbcTemplate.update(sql,id);
    }

}
