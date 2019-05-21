package example.common.services.containers;

import com.google.gson.JsonElement;
import lombok.Data;

@Data
public class MailHogDataContainer {
    private static MailHogDataContainer instance;

    private MailHogDataContainer() {
    }

    public static MailHogDataContainer getInstance() {
        if (instance == null) {
            instance = new MailHogDataContainer();
        }
        return instance;
    }

    private JsonElement messages;
    private JsonElement Allmessages;
//    private MailHogObject mailHogObject;
}
