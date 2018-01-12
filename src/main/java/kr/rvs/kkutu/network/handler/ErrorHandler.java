package kr.rvs.kkutu.network.handler;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.scene.control.Alert;
import kr.rvs.kkutu.EntryKkutu;
import kr.rvs.kkutu.network.PacketHandler;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.in.ErrorPacket;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandler implements PacketHandler {
    private static final Map<String, String> LANG_MAP = new HashMap<>();
    private static final ErrorHandler INSTANCE = new ErrorHandler();

    static {
        JsonObject lang = EntryKkutu.getLang().get("kkutu").getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : lang.entrySet()) {
            LANG_MAP.put(entry.getKey(), entry.getValue().getAsString());
        }
    }

    public static ErrorHandler get() {
        return INSTANCE;
    }

    private ErrorHandler() {
    }

    @Override
    public void handle(Packet packet) {
        if (packet instanceof ErrorPacket) {
            ErrorPacket errorPacket = ((ErrorPacket) packet);
            String code = errorPacket.getCode();
            String defMessage = "알 수 없는 에러: " + code;
            EntryKkutu.showMessage(
                    Alert.AlertType.ERROR,
                    LANG_MAP.getOrDefault("error_" + code, defMessage)
            );
        }
    }
}
