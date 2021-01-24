package sample;

import ADT.*;
import Controller.Controller;
import IType.IType;
import ProgramState.ProgramState;
import Repository.*;
import Statement.IStatement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Exception.*;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerMainWindow {

    private Controller controller;
    private IRepository repository;

    @FXML
    private TextField nrProgramStates;

    @FXML
    private TableView<TableRow> HeapTable, SymbolsTable;

    @FXML
    private ListView OutList, FileTable, programStateIndex, ExecutionStack;

    @FXML
    private Button SwitchToPrograms, runOneStep;

    @FXML
    public void handleSwitchToProgramsButton(javafx.event.ActionEvent event) throws IOException, MyException{
        Stage stage;
        Parent root;
        stage = (Stage)SwitchToPrograms.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Programs.fxml"));

        Scene scene = new Scene(root, 900, 900);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleButtonRunOneStep(javafx.event.ActionEvent event) throws IOException, MyException {
        try {
            controller.oneStepExecution();
        }catch(Exception exc){
            Alert alert = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.OK);
            alert.showAndWait();
            this.handleSwitchToProgramsButton(null);
        }

        ArrayList<ArrayList<String>> heapTableTemp = new ArrayList<>();
        ArrayList<String> outputTemp = new ArrayList<>();
        ArrayList<ArrayList< String >>fileTableTemp = new ArrayList<>();
        ArrayList<String> fileTableTemp2 = new ArrayList<>();
        ArrayList<Integer> programStateIdentifierTemp = new ArrayList<>();
        ArrayList<ArrayList<String>> symbolTableTemp = new ArrayList<>();
        ArrayList<String> executionStackTemp = new ArrayList<>();


        for(var x:repository.get_all()) {
            heapTableTemp.addAll(x.get_heap().getElementsStrings());
            outputTemp.addAll(x.get_out().getElementsStrings());
            fileTableTemp.addAll(x.get_fileTable().getElementsStrings());
            programStateIdentifierTemp.add(x.getId());
            symbolTableTemp.addAll(x.get_symTable().getElementsStrings());
            executionStackTemp.addAll(x.get_exeStack().getElementsStrings());
        }

        for(var y:controller.completedPrograms) {
            heapTableTemp.addAll(y.get_heap().getElementsStrings());
            outputTemp.addAll(y.get_out().getElementsStrings());
            fileTableTemp.addAll(y.get_fileTable().getElementsStrings());
//            programStateIdentifierTemp.add(y.getId());
            symbolTableTemp.addAll(y.get_symTable().getElementsStrings());
            executionStackTemp.addAll(y.get_exeStack().getElementsStrings());
        }

        ArrayList<TableRow> row = new ArrayList<>();
        for(var zz:heapTableTemp){
            row.add(new TableRow(zz.get(0), zz.get(1)));
        }

        ArrayList<TableRow> row2 = new ArrayList<>();
        for(var zz:symbolTableTemp){
            row2.add(new TableRow(zz.get(0), zz.get(1)));
        }

        for(var z:fileTableTemp){
            fileTableTemp2.add(z.get(0));
        }

        HeapTable.setItems(FXCollections.observableList(row));
        OutList.setItems(FXCollections.observableList(outputTemp));
        FileTable.setItems(FXCollections.observableList(fileTableTemp2));
        programStateIndex.setItems(FXCollections.observableList(programStateIdentifierTemp));
        SymbolsTable.setItems(FXCollections.observableList(row2));
        ExecutionStack.setItems(FXCollections.observableList(executionStackTemp));

        nrProgramStates.setText(Integer.toString(repository.get_all().size()));
    }

    public void initData(IStatement selectedItem) {
        try {
            selectedItem.typeCheck(new MyDictionary<String, IType>());
        } catch (MyException e) {
            e.printStackTrace();
        }

        ProgramState p = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), selectedItem);
        repository = new Repository("program14.txt");
        repository.add(p);
        controller = new Controller(repository);
        nrProgramStates.setText(Integer.toString(repository.get_all().size()));
    }
}
