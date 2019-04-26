package net.dongliu.apk.parser.bean;

public class MetaData {
    public String name;
    public String value;

    public MetaData(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + "=" + value;
    }
}
