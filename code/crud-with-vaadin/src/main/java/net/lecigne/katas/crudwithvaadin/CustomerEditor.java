package net.lecigne.katas.crudwithvaadin;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout implements KeyNotifier {

    private final CustomerRepository repository;

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");

    // TODO why more code?
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private ChangeHandler changeHandler;

    @Autowired
    public CustomerEditor(CustomerRepository repository) {
        this.repository = repository;
        add(firstName, lastName, actions);

        // Configure and style components
        setSpacing(true);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        // Wire actions
        save.addClickListener(e -> saveCustomer());
        cancel.addClickListener(e -> setVisible(false));
        delete.addClickListener(e -> deleteCustomer());

        // Invisible initially
        setVisible(false);
    }

    void saveCustomer() {
        changeHandler.onChange();
    }

    public final void editCustomer() {
        setVisible(true);
    }

    void deleteCustomer() {
        changeHandler.onChange();
    }

    @FunctionalInterface
    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(ChangeHandler changeHandler) {
        this.changeHandler = changeHandler;
    }
}
