package com.picasso.util;

import com.picasso.gui.theme.BaseTheme;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

public class Fonts {
    public static Optional<Font> loadFont(String name) {
        try {
            InputStream inputStream = BaseTheme.class.getResourceAsStream(name);
            Objects.requireNonNull(inputStream);
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            return Optional.of(font);
        } catch (NullPointerException | FontFormatException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
