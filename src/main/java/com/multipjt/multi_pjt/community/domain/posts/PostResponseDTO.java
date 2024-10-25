package com.multipjt.multi_pjt.community.domain.posts;



import java.util.Date;
import java.util.List;

import com.multipjt.multi_pjt.community.domain.comments.CommentResponseDTO;

import lombok.Data;

@Data
public class PostResponseDTO {
    private int     post_id;
    private String  post_title;
    private String  post_contents;
    private Date    post_date;
    private Date    post_modify_date;
    private String  post_hashtag;
    private int     post_like_counts;
    private int     post_views;
    private String  post_sport;
    private String  post_sports_keyword;
    private String  post_img1;
    private String  post_img2;
    
    // 댓글 목록
    private List<CommentResponseDTO> comments; 
    
    // 외래키
    private int member_id;
    
    private String post_nickname;
}