package com.readme.sections.enums;

public enum SerializationDays {
    월("Monday", "월"),
    화("Tuesday", "화"),
    수("Wednesday", "수"),
    목("Thursday", "목"),
    금("Friday", "금"),
    토("Saturday", "토"),
    일("Sunday", "일");

    private String englishDay;
    private String shortDay;

    SerializationDays(String englishDay, String shortDay) {
        this.englishDay = englishDay;
        this.shortDay = shortDay;
    }

    public String getEnglishDay() {
        return this.englishDay;
    }

    public String getShortDay() {
        return this.shortDay;
    }

    public static SerializationDays fromKorean(String koreanDay) {
        for (SerializationDays day : SerializationDays.values()) {
            if (day.name().equals(koreanDay)) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid day: " + koreanDay);
    }
}
