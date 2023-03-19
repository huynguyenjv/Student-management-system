package org.swing;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.swing.view.Home;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            new Home();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}