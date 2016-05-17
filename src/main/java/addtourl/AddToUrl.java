package addtourl;

import java.util.Map;
import java.util.stream.Collectors;

public class AddToUrl {
    public static String addQueryParametersToUrl(String path, Map<String, String> parameters) {
        String modifiedPath = path;
        if (!parameters.isEmpty()) {
            modifiedPath += path.contains("?") ? "" : "?";
            modifiedPath += modifiedPath.endsWith("?") || modifiedPath.endsWith("&") ? "" : "&";
            modifiedPath += parameters.entrySet().stream().map(
                    e -> e.getKey() + '=' + e.getValue()
            ).collect(Collectors.joining("&"));
        }
        return modifiedPath;
    }
}
