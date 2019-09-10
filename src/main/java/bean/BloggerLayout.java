package bean;

/**
 * 状态对象
 */
public class BloggerLayout  {
    private String tableName;

    private long fiexdContentId;

    private int fiexdRank;

    private long fiexdPlace;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

    public long getFiexdPlace() {
        return fiexdPlace;
    }

    public void setFiexdPlace(long fiexdPlace) {
        this.fiexdPlace = fiexdPlace;
    }
}