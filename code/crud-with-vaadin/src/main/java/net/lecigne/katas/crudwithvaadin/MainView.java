package net.lecigne.katas.crudwithvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

@Route
public class MainView extends VerticalLayout {
    private final CustomerRepository repo;
    final Grid<Customer> grid;
    private final CustomerEditor editor;
    private final Button addNewBtn;

    public MainView(CustomerRepository repo, CustomerEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Customer.class);
        this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by last name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        // Accessing the editor
        grid.asSingleSelect().addValueChangeListener(e -> editor.editCustomer());
        addNewBtn.addClickListener(e -> editor.editCustomer());

        // Implement the editor's change handler
        editor.setChangeHandler(() -> editor.setVisible(false));

        listCustomers("");
    }

    private void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        } else {
            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
}
