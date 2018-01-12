package kr.rvs.kkutu.network.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Alert;
import kr.rvs.kkutu.EntryKkutu;
import kr.rvs.kkutu.game.holder.RoomHolder;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.game.room.RoomPlayer;
import kr.rvs.kkutu.network.PacketHandler;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.in.*;
import kr.rvs.kkutu.network.packet.out.ChatOutPacket;

import java.io.*;
import java.util.*;

public class BotHandler implements PacketHandler {
    private static final Map<Character, Set<String>> dataMap = new HashMap<>();
    private final Set<String> remainSet = new HashSet<>();
    private Room room;
    private String lastChar = "!@#!@%@!%1!%@";

    private static File getDataFile() {
        return new File("data.json");
    }

    private static void save() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getDataFile())))) {
            new Gson().toJson(dataMap, writer);
            EntryKkutu.showMessage(Alert.AlertType.INFORMATION, dataMap.size() + " 개 저장 완료.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        Gson gson = new Gson();
        try {
            Map<Character, Set<String>> map = gson.fromJson(new FileReader(getDataFile()), new TypeToken<HashMap<Character, TreeSet<String>>>() {
            }.getType());
            if (map != null) {
                for (Map.Entry<Character, Set<String>> entry : map.entrySet()) {
                    Set<String> set = new TreeSet<>((o1, o2) -> Integer.compare(o2.length(), o1.length()));
                    set.addAll(entry.getValue());
                    dataMap.put(entry.getKey(), set);
                }
            }
        } catch (FileNotFoundException e) {
            // Ignore
        }

        Runtime.getRuntime().addShutdownHook(new Thread(BotHandler::save));
    }

    private void clear() {
        lastChar = "!@#!@%@!%1!%@";
    }

    private Set<String> getData(Character character) {
        return dataMap.computeIfAbsent(character, key -> new TreeSet<>((o1, o2) -> Integer.compare(o2.length(), o1.length())));
    }

    private void store(GameTurnEndPacket packet) {
        String word = packet.getWordAnyway();
        if (word != null) {
            Set<String> words = getData(word.charAt(0));
            if (words.add(word)) {
                room.getController().chat("배운 단어: " + word);
            }
        }
    }

    private boolean useable(String word) {
        return !remainSet.contains(word);
    }

    private Optional<String> getWord(Character character) {
        for (String word : getData(character)) {
            if (useable(word)) {
                return Optional.ofNullable(word);
            }
        }
        return Optional.empty();
    }

    private boolean myTurn(GameTurnStartPacket packet) {
        RoomPlayer player = room.getPlayer(packet.getTurn());
        return EntryKkutu.isMe(player);
    }

    private void send(PacketManager manager, String word) {
        manager.sendPacket(new ChatOutPacket(word, true));
        remainSet.add(word);
    }

    @Override
    public void handle(PacketManager manager, Packet packet) {
        if (packet instanceof GameStartPacket) {
            GameStartPacket gameStartPacket = ((GameStartPacket) packet);
            this.room = RoomHolder.getOrThrow(gameStartPacket.getRoomId());
        } else if (packet instanceof GameTurnStartPacket) {
            GameTurnStartPacket startPacket = (GameTurnStartPacket) packet;
//            if (myTurn(startPacket)) {
                char startChar = startPacket.getStartChar();
                char subChar = startPacket.getSubChar();
                lastChar = String.valueOf(startChar);
                send(manager, getWord(startChar).orElse(getWord(subChar).orElse(startChar + " 몰라 알려줘")));
//            }
        } else if (packet instanceof GameTurnEndPacket) {
            GameTurnEndPacket endPacket = ((GameTurnEndPacket) packet);
            store(endPacket);
            remainSet.clear();
            clear();
        } else if (packet instanceof GameEndPacket) {
            new Thread(BotHandler::save).start();
        } else if (packet instanceof ChatInPacket) {
            ChatInPacket chatPacket = ((ChatInPacket) packet);
            String message = chatPacket.getMessage();
            if (!EntryKkutu.isMe(chatPacket.getProfile()) && message.startsWith(lastChar) && !message.contains(" ")) {
                send(manager, message);
            }
        } else if (packet instanceof GameTurnErrorPacket) {
            GameTurnErrorPacket errorPacket = ((GameTurnErrorPacket) packet);
            if (EntryKkutu.isMe(errorPacket.getProfile())) {
                getWord(lastChar.charAt(0)).ifPresent(word -> send(manager, word));
            }
        }
    }
}
