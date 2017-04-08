package Helpers;

public class Constants {
    public static final String TYPE_POSTE_RESTANTE = "До востребования";
    public static final String TYPE_UNGENT = "Срочный";
    public static final String TYPE_SETTLEMENT = "Расчетный";
    public static final String TYPE_ACCUMULATIVE = "Накопительный";
    public static final String TYPE_SAVINGS = "Сберегательный";
    public static final String TYPE_METAL = "Металлический";

    public static final int PORT = Integer.parseInt(ConfigProperties.getProperty("port").trim());
    public static final String SERVER_ADDRESS = ConfigProperties.getProperty("server_address").trim();
    public static final String KEEP_ALIVE_SEQUENCE = ConfigProperties.getProperty("keep_alive_sequence").trim();
    public static final int RESPONSE_TIMEOUT = Integer.parseInt(ConfigProperties.getProperty("response_timeout").trim());
    public static final String DATA_FILE = ConfigProperties.getProperty("data_file").trim();
}
