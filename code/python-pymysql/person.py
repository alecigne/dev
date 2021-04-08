class Person():

    def __init__(self, id_person, first_name, last_name, age, sex, income):
        self.id_person = id_person
        self.first_name = first_name
        self.last_name = last_name
        self.age = age
        self.sex = sex
        self.income = income

    def __str__(self):
        return (f'ID: {self.id_person}, '
                f'First name: {self.first_name}, '
                f'Last name: {self.last_name}, '
                f'Age: {self.age}, '
                f'Sex: {self.sex}, '
                f'Income: {self.income}')
