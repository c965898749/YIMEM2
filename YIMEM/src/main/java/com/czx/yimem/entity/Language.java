package com.czx.yimem.entity;

public class Language {
    private Integer id;

    private Integer userId;

    private String language;
    private String languageName;

    private String speakAbility;

    private String speakAbilityName;

    private String writeAbility;

    private String writeAbilityName;

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getSpeakAbilityName() {
        return speakAbilityName;
    }

    public void setSpeakAbilityName(String speakAbilityName) {
        this.speakAbilityName = speakAbilityName;
    }

    public String getWriteAbilityName() {
        return writeAbilityName;
    }

    public void setWriteAbilityName(String writeAbilityName) {
        this.writeAbilityName = writeAbilityName;
    }

    public Language(Integer userId) {
        this.userId = userId;
    }

    public Language(Integer id, Integer userId, String language, String speakAbility, String writeAbility) {
        this.id = id;
        this.userId = userId;
        this.language = language;
        this.speakAbility = speakAbility;
        this.writeAbility = writeAbility;
    }

    public Language() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getSpeakAbility() {
        return speakAbility;
    }

    public void setSpeakAbility(String speakAbility) {
        this.speakAbility = speakAbility == null ? null : speakAbility.trim();
    }

    public String getWriteAbility() {
        return writeAbility;
    }

    public void setWriteAbility(String writeAbility) {
        this.writeAbility = writeAbility == null ? null : writeAbility.trim();
    }
}
