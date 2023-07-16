package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

class CustomerDetailsController {

    @FXML
    private TextField customerNameTextField;

    @FXML
    private CheckBox foodQueueCheckBox;

    @FXML
    private CheckBox waitingListCheckBox;

    @FXML
    private void searchButtonClicked() {
        String customerName = customerNameTextField.getText();
        boolean searchInFoodQueue = foodQueueCheckBox.isSelected();
        boolean searchInWaitingList = waitingListCheckBox.isSelected();

        // TODO: Perform search and display customer details based on search criteria
    }
}