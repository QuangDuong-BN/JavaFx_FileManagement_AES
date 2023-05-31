package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class FileExplorer extends Application {
    private ListView<String> fileList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Explorer");

        Label titleLabel = new Label("Files in Directory");
        fileList = new ListView<>();
        fileList.setOnMouseClicked(this::handleMouseClicked);

        VBox vbox = new VBox(titleLabel, fileList);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);

        // Thay đổi đường dẫn thư mục tại đây
        String directoryPath = "/home/quang/Downloads";
        displayFilesInDirectory(directoryPath);

        primaryStage.show();
    }

    private void displayFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                fileList.getItems().add(file.getName());
            }
        }
    }

    private void handleMouseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            String selectedFile = fileList.getSelectionModel().getSelectedItem();
            if (selectedFile != null) {
                showContextMenu(event.getScreenX(), event.getScreenY(), selectedFile);
            }
        }
    }

    private void showContextMenu(double x, double y, String selectedFile) {
        MenuItem deleteMenuItem = new MenuItem("Delete");
        MenuItem renameMenuItem = new MenuItem("Rename");

        deleteMenuItem.setOnAction(e -> {
            // Xử lý logic xóa tệp tin
            System.out.println("Deleting file: " + selectedFile);
        });

        renameMenuItem.setOnAction(e -> {
            // Xử lý logic đổi tên tệp tin
            System.out.println("Renaming file: " + selectedFile);
        });

        ContextMenu contextMenu = new ContextMenu(deleteMenuItem, renameMenuItem);
        contextMenu.show(fileList, x, y);
    }
}

