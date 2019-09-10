package bean;

/**
 * 固定地点对象
 */
public class FixedLocation {
    private long fixedId;

    private String fixedName;

    public long getFixedId() {
        return fixedId;
    }

    public void setFixedId(long fixedId) {
        this.fixedId = fixedId;
    }

    public String getFixedName() {
        return fixedName;
    }

    public void setFixedName(String fixedName) {
        this.fixedName = fixedName == null ? null : fixedName.trim();
    }
}