package com.intelixcloudmessenger.producer.domain.messenger;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(creatorVisibility = ANY, fieldVisibility = ANY)
public class MailMessenger {

    private String subject;
    private String content;
    private List<String> to;

    @JsonCreator
    public MailMessenger(
        @JsonProperty("subject") String subject,
        @JsonProperty("age") String content,
        @JsonProperty("to") List<String> to) {
        this.subject = subject;
        this.content = content;
        this.to = to;
    }

    public List<String> getTo() {
        return to;
    }

    public void addTo(String to) {
        this.to.add(to);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "MailMessenger{" +
        "to=" + to +
        ", subject='" + subject + '\'' +
        ", content='" + content + '\'' +
        '}';
    }
}
