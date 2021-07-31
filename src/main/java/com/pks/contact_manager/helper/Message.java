package com.pks.contact_manager.helper;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Message {
    private String content;
    private String type;

}
