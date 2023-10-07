package com.minifacebookbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "unLike")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UnLike {
    @Id
    private String id;
    private String userId;
    private String postId;
    private String createdAt;
}