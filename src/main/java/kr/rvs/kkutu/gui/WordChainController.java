package kr.rvs.kkutu.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import kr.rvs.kkutu.EntryKkutu;
import kr.rvs.kkutu.game.GameProcessorFactory;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.in.GameEndPacket;
import kr.rvs.kkutu.network.packet.in.GameReadyPacket;
import kr.rvs.kkutu.network.packet.in.GameTurnEndPacket;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WordChainController implements GameProcessor {
    public static final GameProcessorFactory FACTORY = new GameProcessorFactory() {
        @Override
        public GameProcessor create(RoomController controller) {
            return new WordChainController(controller);
        }

        @Override
        public URL fxmlUrl() {
            return WordChainController.class.getResource("/part/GameWordChain.fxml");
        }
    };
    private final RoomController roomController;
    public VBox vBox;
    public Text wordText;
    public ProgressBar timer;
    public TilePane playerTilePane;
    private boolean initialized = false;

    public WordChainController(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialized = true;
    }

    @Override
    public void handle(PacketManager manager, Packet packet) {
        if (packet instanceof GameReadyPacket) {
            GameReadyPacket readyPacket = ((GameReadyPacket) packet);
            String startChar = readyPacket.getSubChar() != null
                    ? String.format("%s(%s)", readyPacket.getStartChar(), readyPacket.getSubChar())
                    : String.valueOf(readyPacket.getStartChar());
            wordText.setText(startChar);
            wordText.setFill(Color.BLACK);
        } else if (packet instanceof GameTurnEndPacket) {
            GameTurnEndPacket endPacket = ((GameTurnEndPacket) packet);
            wordText.setText(endPacket.isPass()
                    ? endPacket.getWord()
                    : endPacket.getHint());
            wordText.setFill(endPacket.isPass() ? Color.GREEN : Color.RED);
        } else if (packet instanceof GameEndPacket) {
            roomController.update();
        }
    }

    @Override
    public void update(Room room) {
        if (!initialized) {
            return;
        }

        EntryKkutu.runOnMain(() -> {
            boolean ingame = room.isIngame();
            roomController.userTilePane.setVisible(!ingame);
            vBox.setVisible(ingame);

            playerTilePane.getChildren().clear();
            room.getGame().getPlayers().stream()
                    .map(room::getPlayerOrThrow)
                    .forEach(player -> {
                        FXMLLoader loader = new FXMLLoader(WordChainController.class.getResource("/part/GameUserTile.fxml"));
                        loader.setController(new GamePlayerTileController(player));
                        try {
                            Node node = loader.load();
                            playerTilePane.getChildren().add(node);
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    });
        });
    }
}
