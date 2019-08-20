package bean;

public class BloggerLayout {
    private long id;

    private String tableName;

    private long fiexdContentId;

    private int fiexdRank;

    private String fiexdPlace;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public long getFiexdContentId() {
        return fiexdContentId;
    }

    public void setFiexdContentId(long fiexdContentId) {
        this.fiexdContentId = fiexdContentId;
    }

    public int getFiexdRank() {
        return fiexdRank;
    }

    public void setFiexdRank(int fiexdRank) {
        this.fiexdRank = fiexdRank;
    }

    public String getFiexdPlace() {
        return fiexdPlace;
    }

    public void setFiexdPlace(String fiexdPlace) {
        this.fiexdPlace = fiexdPlace == null ? null : fiexdPlace.trim();
    }
}