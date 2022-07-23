package ru.javarush.cryptoanalyser.shubchynskyi.constans;

public class Strings {
    private Strings(){}

    private static final String rus = "йцукеёнгшщзхъфывапролджэячсмитьбю";
    private static final String eng = "qwertyuiopasdfghjklzxcvbnm";
    private static final String cyphers = "1234567890";
    private static final String symbols = "!@#$%^&*()_+-=[]{}:;',./<>|?№ ~";

    public static final String ALPHABET = rus + rus.toUpperCase()
            + eng + eng.toUpperCase()
            + cyphers + symbols;

    public static final String INVALIDATORS = "ыЫъЪьЬ";

    private static final String ENCODE_INFO = "\"Encode\" - encrypt from file to file using key";
    private static final String DECODE_INFO = "\"Decode\"- decrypt from file to file using key";
    private static final String BRUTEFORCE_INFO = "\"Bruteforce\" - decrypt from file to file using brute force";
    private static final String CRYPTANALYSIS_INFO = "\"CryptoAnalysis\" - decrypt from file to file using statistical analysis";

    public static final String HELP_INFO = ENCODE_INFO + "\n"
           + DECODE_INFO + "\n"
           + BRUTEFORCE_INFO + "\n"
           + CRYPTANALYSIS_INFO + "\n";

    public static final String MESSAGE_FILE_READ_ERROR = "File read error";
    public static final String MESSAGE_IO_ERROR = "I/O Error";
    public static final String MESSAGE_RESULT_IS_OK = "Read all bytes ";
    public static final String MESSAGE_RESULT_VALIDATION_IS_FAIL = "Validation if fail!";

}
