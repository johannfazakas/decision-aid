package ro.johann.dm.test.api;

public class Storage {
    private static int responseStatus;


    public static int getResponseStatus() {
        return responseStatus;
    }

    public static void setResponseStatus(int responseStatus) {
        Storage.responseStatus = responseStatus;
    }
}
