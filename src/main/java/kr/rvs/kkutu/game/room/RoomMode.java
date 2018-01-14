package kr.rvs.kkutu.game.room;

import kr.rvs.kkutu.game.GameProcessorFactory;
import kr.rvs.kkutu.gui.GameProcessor;
import kr.rvs.kkutu.gui.RoomController;
import kr.rvs.kkutu.gui.WordChainController;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.Packet;

import java.net.URL;
import java.util.ResourceBundle;

public enum RoomMode {
    EKT("영어 끄투"),
    ESH("영어 끝말잇기", WordChainController.FACTORY),
    KKT("한국어 쿵쿵따"),
    KSH("한국어 끝말잇기", WordChainController.FACTORY),
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
    IMG("그림퀴즈");

    private final String name;
    private final GameProcessorFactory factory;

    RoomMode(String name, GameProcessorFactory factory) {
        this.name = name;
        this.factory = factory;
    }

    RoomMode(String name) {
        this(name, new GameProcessorFactory() {
            @Override
            public GameProcessor create(RoomController controller) {
                return new GameProcessor() {
                    @Override
                    public void update(Room room) {

                    }

                    @Override
                    public void initialize(URL location, ResourceBundle resources) {

                    }

                    @Override
                    public void handle(PacketManager manager, Packet packet) {

                    }
                };
            }

            @Override
            public URL fxmlUrl() {
                return null;
            }
        });
    }

    public String getName() {
        return name;
    }

    public GameProcessorFactory getProcessorFactory() {
        return factory;
    }

    @Override
    public String toString() {
        return getName();
    }
}
