package net.lecigne.katas.crudwithvaadin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout implements KeyNotifier {

    private final CustomerRepository repository;

    /**
     * The currently edited customer
     */
    private Customer customer;

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");

    // TODO why more code?
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Customer> binder = new Binder<>(Customer.class);
    private ChangeHandler changeHandler;

    @Autowired
    public CustomerEditor(CustomerRepository repository) {
        this.repository = repository;
        add(firstName, lastName, actions);

        // Bind instance fields to the customer class
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> saveCustomer());

        // Wire actions
        save.addClickListener(e -> saveCustomer());
        cancel.addClickListener(e -> editCustomer(customer)); // go back to editing the current field instance
        delete.addClickListener(e -> deleteCustomer());

        // Invisible initially
        setVisible(false);
    }

    void saveCustomer() {
        repository.save(customer);
        changeHandler.onChange();
    }

    public final void editCustomer(Customer customer) {
        if (customer == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = customer.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            repository.findById(customer.getId()).ifPresent(c -> this.customer = c);
        }
        else {
            this.customer = customer;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        binder.setBean(this.customer);

        setVisible(true);

        // Focus first name initially
        firstName.focus();
    }

    void deleteCustomer() {
        repository.delete(customer);
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
