import crud
import random
import string
from person import Person


def create_name(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


def create_person():
    first_name = create_name(4)
    last_name = create_name(8)
    age = random.randint(1, 100)
    sex = random.choice(['m', 'f'])
    income = random.randint(0, 10000)
    return Person(None, first_name, last_name, age, sex, income)


for i in range(10):
    person = create_person()
    try:
        crud.create(person)
        print(f'Person created: {person}')
    except:
        print('Error.')
