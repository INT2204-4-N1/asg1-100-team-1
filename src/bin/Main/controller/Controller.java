package bin.Main.controller;


                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JavaLayerException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            InputStream sound = null;
                            Audio audio = Audio.getInstance();
                            sound = audio.getAudio(str1, Language.VIETNAMESE);
                            audio.play(sound);
                        } catch (IOException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JavaLayerException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("THÔNG BÁO");
                alert.setHeaderText("KHÔNG PHÁT HIỆN TỪ");
                alert.setContentText("*ERROR : 404");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("KHÔNG CÓ KẾT NỐI INTERNET");
            alert.setContentText("*WARNING: FBI");
            alert.show();
        }
    }

    /**
     *  Load cửa sổ để thao tác vời google dịch
     *  stage.initStyle(StageStyle.UNDECORATED) : xóa các nút Minimize, Maximize, Close
     *  stage.initModality(Modality.APPLICATION_MODAL) : không thẻ tắt cửa số Main khi đang thao tác với cửa sổ Google
     * @param event
     * @throws InterruptedException
     * @throws IOException
     */
    public void loadGoogle(ActionEvent event) throws InterruptedException, IOException {
        if (InternetConnected.IsConnecting() == true) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Style/GoogleLoader.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Hello World");
                Scene scene = new Scene(root1);
                scene.getStylesheets().add(getClass().getResource("../Style/StyleBuilder.css").toExternalForm());
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                Platform.setImplicitExit(false);
                stage.show();
            } catch (Exception e) {
                System.out.println("cant load new window");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO:");
            alert.setHeaderText("KHÔNG CÓ KẾT NỐI INTERNET");
            alert.setContentText("*WARNING: FBI");
            alert.showAndWait();
        }
    }

    /**
     *  phương thhuwcs xử lý cho Button WIkisearch*
     * @param event
     * @throws InterruptedException
     * @throws IOException
     */
    public void setWikiButton(ActionEvent event) throws InterruptedException, IOException {
        if (InternetConnected.IsConnecting() == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO:");
            alert.setHeaderText("KHÔNG CÓ KẾT NỐI INTERNET");
            alert.setContentText("*WARNING: FBI");
            alert.showAndWait();
        } else {
            WebEngine engine = webView.getEngine();
            String str = (String) listView.getSelectionModel().getSelectedItem();
            String str1 = textField.getText();
            if ("".equals(str1)) {
                if (checkTypeDictionary == true) {
                    engine.load("https://en.wiktionary.org/wiki/" + str);
                } else {
                    engine.load("https://vi.wiktionary.org/wiki/" + str);
                }
            } else {
                if (checkTypeDictionary == true) {
                    engine.load("https://en.wiktionary.org/wiki/" + str1);
                } else {
                    engine.load("https://vi.wiktionary.org/wiki/" + str1);
                }
            }
        }
    }

    /**
     *
     * @param event
     * @throws InterruptedException
     * @throws IOException
     */
    public void setLabanButton(ActionEvent event) throws InterruptedException, IOException {
        if (InternetConnected.IsConnecting() == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO:");
            alert.setHeaderText("KHÔNG CÓ KẾT NỐI INTERNET");
            alert.setContentText("*WARNING: FBI");
            alert.showAndWait();
        } else {
            WebEngine engine = webView.getEngine();
            String str = (String) listView.getSelectionModel().getSelectedItem();
            String str1 = textField.getText();
            if ("".equals(str1)) {
                if (checkTypeDictionary == true) {
                    engine.load("https://dict.laban.vn/find?type=1&query=" + str);
                } else {
                    engine.load("https://dict.laban.vn/find?type=2&query=" + str);
                }
            } else {
                if (checkTypeDictionary == true) {
                    engine.load("https://dict.laban.vn/find?type=1&query=" + str1);
                } else {
                    engine.load("https://dict.laban.vn/find?type=2&query=" + str1);
                }
            }
        }
    }
    @Override
    /**
     * Chương trình khởi tạo môi trường cho từ điển
     */
    public void initialize(URL location, ResourceBundle resources) {
        //hàm hiện từ lên listview
        showlistview();
        SearchTextFieldEvent();
        //gợi ý từ tìm kiếm
        searchWord();
        //bắt sự kiện cho listview khi item đc chọn
        chooseitem();
        // bắt sự kiện cho textfield SEARCH
        setCalendarDisplay();
        setClockDisplay();
    }
}
