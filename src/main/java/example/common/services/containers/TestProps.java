package example.common.services.containers;

import org.junit.runner.Description;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Lazy
public class TestProps {
    public static Description des;
    public static String sessionId;
    public static List<String> ListSessionId = new ArrayList<String>();

}
