package components;

public class Paragraph extends Html {

    public Paragraph(String text) {
        super(text);
    }

    @Override
    protected String wrap(String text) {
        return super.wrap("<p>" + text + "</p>");
    }

}
