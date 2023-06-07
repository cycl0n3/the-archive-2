package form;

public class EntryValue<T> {

    private EntryValue() {
    }


    public static class Valid<T> extends EntryValue<T> {

    }

    public static class Invalid<T> extends EntryValue<T> {

    }

    public static class Empty<T> extends EntryValue<T> {

    }


}
