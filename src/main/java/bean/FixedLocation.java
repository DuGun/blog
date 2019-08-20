package bean;

public class FixedLocation {
    private String name;

    private String nameAs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNameAs() {
        return nameAs;
    }

    public void setNameAs(String nameAs) {
        this.nameAs = nameAs == null ? null : nameAs.trim();
    }
}