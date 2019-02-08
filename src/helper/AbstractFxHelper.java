/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.DaoEngigne;

/**
 *
 *  @author cneree
 * @param <T>
 */
public abstract class AbstractFxHelper<T> {

    protected AbstractFxHelperItem[] abstractFxHelperItem;
    protected List<T> list = new ArrayList<T>();
    protected TableView<T> table;
    private ObservableList<T> data;

    public TableView<T> getTable() {
        return table;
    }

    public void setTable(TableView<T> table) {
        this.table = table;
    }

    public AbstractFxHelper(AbstractFxHelperItem[] abstractFxHelperItem, TableView<T> table) {
        this.abstractFxHelperItem = abstractFxHelperItem;
        this.table = table;
        setupTable();
    }

    public AbstractFxHelper(AbstractFxHelperItem[] abstractFxHelperItem, TableView<T> table, List<T> list) {
        this.abstractFxHelperItem = abstractFxHelperItem;
        this.table = table;
        this.list = list;
        setupTable();
    }

    public void select(T t) {
        table.getSelectionModel().select(t);
    }

    private void setupTable() {
        for (int i = 0; i < abstractFxHelperItem.length; i++) {
            TableColumn<T, ?> column = new TableColumn<>(abstractFxHelperItem[i].getColumnName());
            column.setCellValueFactory(new PropertyValueFactory<>(abstractFxHelperItem[i].getAttributeName()));
            table.getColumns().add(column);
        }
        data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    public void create(T t) {
        list.add(t);
        data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    public void remove(T t) {
        list.remove(t);
        data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    public T getSelected() {
        return table.getSelectionModel().getSelectedItem();
    }

    public void setList(List<T> list) {
        data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }
    public T getValueAt(int rowIndex) {
        if (list!=null && rowIndex < list.size()) {
            return list.get(rowIndex);
        }

        return null;
    }
    
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (list!=null && rowIndex < list.size()) {
            for (int i = 0; i < abstractFxHelperItem.length; i++) {
                if (columnIndex == i) {
                    System.out.println("ana daba f i ==> "+i);
                    try {
                        return DaoEngigne.lunchGetterByParamName(list.get(rowIndex), abstractFxHelperItem[i].getAttributeName());
                    } catch (Exception ex) {
                    }
                }
            }
        }
        return null;
    }

}
