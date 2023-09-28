package com.minifacebookbackend.domain.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageCommand {
 private String id;
 private String url;
 private String postId;
 @JsonProperty("isNew")
 private boolean isNew;
}
