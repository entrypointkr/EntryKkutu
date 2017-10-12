package kr.rvs.kkutu.game;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public enum RoomType {
    영어끄투("영어 끄투"),
    영어끝말("영어 끝말잇기"),
    한글쿵쿵따("한국어 쿵쿵따"),
    한글끝말("한국어 끝말잇기"),
    자음퀴즈("자음퀴즈"),
    한글십자말("한국어 십자말풀이"),
    한글타자("한국어 타자 대결"),
    영어타자("영어 타자 대결"),
    한글앞말("한국어 앞말잇기"),
    훈민정음("훈민정음"),
    한글단어("한국어 단어대결"),
    한글솎솎("한국어 솎솎"),
    영어솎솎("영어 솎솎"),
    ;

    private final String name;

    RoomType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static RoomType getById(int mode) {
        RoomType[] types = values();
        return types.length > mode ?
                types[mode] : null;
    }
}
