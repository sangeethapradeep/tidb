import java.nio.charset.StandardCharsets;
import org.tikv.common.TiConfiguration;
import org.tikv.common.TiSession;
import org.tikv.raw.RawKVClient;
import org.tikv.shade.com.google.protobuf.ByteString;

public class TiKvApp {

  public static void main(String[] args) {
    String pdAddress = "127.0.0.1:2379";
    TiConfiguration conf = TiConfiguration.createRawDefault(pdAddress);


    try(TiSession session = TiSession.create(conf)) {
      try(RawKVClient client = session.createRawClient()) {
        client.put(ByteString.copyFromUtf8("Hello"), ByteString.copyFromUtf8("World"));
        ByteString value = client.get(ByteString.copyFromUtf8("Hello"));
        System.out.println("Key-Value: " + "Hello - " + value.toString(StandardCharsets.UTF_8));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
