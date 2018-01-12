package kr.rvs.kkutu.game.room;

public class RoomData {
    private final Room room;

    public RoomData(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public String getId() {
        return room.getId();
    }

    public String getTitle() {
        return room.getTitle();
    }

    public RoomMode getMode() {
        return room.getMode();
    }

    public int getRound() {
        return room.getRound();
    }

    public int getTime() {
        return room.getTime();
    }

    public String getSize() {
        return String.format("%s/%s", room.getPlayerAmount(), room.getLimit());
    }

    public String getPassword() {
        return room.isPassword() ? "O" : "X";
    }
}
