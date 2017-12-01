package com.Itsu.Comet.listener;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.text.BadLocationException;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.gui.TextPanePopup;
import com.Itsu.Comet.utils.Utils;

/**
 * 
 * <h6>Comet project</h6>
 * <p>for PMMP/Jupiter/Nukkit plugin
 * 
 * <p>Java（PHP）構文向けIDEプロジェクト
 * <p>Made by Itsu(Twitter: @itsu_dev)
 * 
 * @author Itsu
 *
 */

public class TextPanePopupListener implements ActionListener {

    private TextPanePopup popup;

    public TextPanePopupListener(TextPanePopup popup) {
        this.popup = popup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {

            case "save":
                try {
                    String path = Controller.getNowProjectPath();
                    Utils.writeFile(new File(path), popup.getEditorPanel().getEditor().getText());
                } catch (IOException e1) {
                    Controller.exception("保存中にエラーが発生しました。", e1);
                }
                break;

            case "paste":
                try {

                    int offset = popup.getEditorPanel().getEditor().getCaretPosition();
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable object = clipboard.getContents(null);
                    String str1 = (String)object.getTransferData(DataFlavor.stringFlavor);
                    popup.getEditorPanel().getEditor().getDocument().insertString(offset, str1, null);

                } catch (UnsupportedFlavorException | IOException | BadLocationException e1) {
                    Controller.error("クリップボードの内容をペーストすることはできません。");
                }

                break;

            case "choose":
                popup.getEditorPanel().getEditor().selectAll();
                break;

        }
    }

}
