package kr.rvs.kkutu.game.room;

public enum RoomMode {
    EKT("영어 끄투"),
    ESH("영어 끝말잇기"),
    KKT("한국어 쿵쿵따"),
    KSH("한국어 끝말잇기"),
    CSQ("자음퀴즈"),
    KCW("한국어 십자말풀이"),
    KTY("한국어 타자 대결"),
    ETY("영어 타자 대결"),
    KAP("한국어 앞말잇기"),
    HUN("훈민정음"),
    KDA("한국어 단어 대결"),
    EDA("영어 단어 대결"),
    KSS("한국어 솎솎"),
    ESS("영어 솎솎"),
    IMG("그림퀴즈")
    ;
    private final String name;

    RoomMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return getName();
    }
}
