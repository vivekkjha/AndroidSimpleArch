package mds.mdstoolkit.model;

/**
 * Created by dummy on 12/23/15.
 */
public class MessageEvent {
    String type;
    public static final String NETWORK_STATUS_CHANGED = "network_status_changed";

    public void setType(String type) {
        this.type = type;
    }
    Object data;
    Object data2;
    public void setData(Object data) {
        this.data = data;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    public Object getData2() {
        return data2;
    }

    public Object getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public MessageEvent(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s", this.type, this.data, this.data2);
    }

}
