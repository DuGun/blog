package bean;

public class BloggerLayout {
    private String id;

    private String tableName;

    private String fiexdContentId;

    private Integer fiexdRand;

    private String fiexdPlace;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getFiexdContentId() {
        return fiexdContentId;
    }

    public void setFiexdContentId(String fiexdContentId) {
        this.fiexdContentId = fiexdContentId == null ? null : fiexdContentId.trim();
    }

    public Integer getFiexdRand() {
        return fiexdRand;
    }

    public void setFiexdRand(Integer fiexdRand) {
        this.fiexdRand = fiexdRand;
    }

    public String getFiexdPlace() {
        return fiexdPlace;
    }

    public void setFiexdPlace(String fiexdPlace) {
        this.fiexdPlace = fiexdPlace == null ? null : fiexdPlace.trim();
    }
}