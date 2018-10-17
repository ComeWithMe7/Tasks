import java.io.Serializable;
import java.util.*;
public class Card implements Serializable {

    private Date date = new Date();
    private Account account;
    private boolean isBlocked;

    Card(Account acc) {
        date = new Date();
        isBlocked = false;
        account = acc;
    }
    public Date getDate() {
        return date;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        this.isBlocked = blocked;
    }
}
