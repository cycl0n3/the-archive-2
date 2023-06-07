package com.picasso.app;

import com.picasso.gui.PFrame;
import com.picasso.gui.PImageEditorPanel;
import com.picasso.gui.PDesktop;
import com.picasso.gui.PRootPanel;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileMenu {

    public static void open() {
        PRootPanel rootPanel = PRootPanel.getRootPanel();
        PDesktop desktop = rootPanel.getDesktop();

        chooseImageFiles()
                .map(ImageEditor::new)
                .filter(ImageEditor::isLoadedProperly)
                .forEach(imageEditor -> desktop
                        .addImageEditor(new PImageEditorPanel(imageEditor)));
        PFrame.getMain().requestFocus();
    }

    private static Stream<File> chooseImageFiles() {
        FileDialog fileDialog = new FileDialog(PFrame.getMain());
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setMultipleMode(true);
        fileDialog.setFilenameFilter((dir, name) -> name.matches(".*\\.(jpg|jpeg|png|bmp)"));
        fileDialog.setVisible(true);
        return Arrays.stream(fileDialog.getFiles());
    }

}
