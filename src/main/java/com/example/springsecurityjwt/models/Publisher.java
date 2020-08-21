package com.example.springsecurityjwt.models;

import com.example.springsecurityjwt.enums.PublisherTypeEnum;

public class Publisher {
    private String name;
    private PublisherTypeEnum publisherTypeEnum;

    public Publisher() {
    }

    public Publisher(String name, PublisherTypeEnum publisherTypeEnum) {
        this.name = name;
        this.publisherTypeEnum = publisherTypeEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublisherTypeEnum getPublisherTypeEnum() {
        return publisherTypeEnum;
    }

    public void setPublisherTypeEnum(PublisherTypeEnum publisherTypeEnum) {
        this.publisherTypeEnum = publisherTypeEnum;
    }
}
