package Helpers;

import java.util.HashMap;

public class Constants {
    public static final HashMap<String, String> TYPES = new HashMap<>();
    static
    {
        TYPES.put("TYPE_POSTE_RESTANTE", "До востребования");
        TYPES.put("TYPE_UNGENT", "Срочный");
        TYPES.put("TYPE_SETTLEMENT", "Расчетный");
        TYPES.put("TYPE_ACCUMULATIVE", "Накопительный");
        TYPES.put("TYPE_SAVINGS", "Сберегательный");
        TYPES.put("TYPE_METAL", "Металлический");
    }

    public static final int PORT = Integer.parseInt(ConfigProperties.getProperty("port").trim());
    public static final String SERVER_ADDRESS = ConfigProperties.getProperty("server_address").trim();
    public static final String KEEP_ALIVE_SEQUENCE = ConfigProperties.getProperty("keep_alive_sequence").trim();
    public static final int RESPONSE_TIMEOUT = Integer.parseInt(ConfigProperties.getProperty("response_timeout").trim());
    public static final String DATA_FILE = ConfigProperties.getProperty("data_file").trim();
}
