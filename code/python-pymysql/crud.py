import pymysql
from config import cfg
from person import Person

mysql_config = cfg['mysql']

SERVER = mysql_config['host']
USER = mysql_config['user']
PASSWORD = mysql_config['passwd']
DB = mysql_config['db']
PORT = mysql_config['port']


def connect():
    db = pymysql.connect(host=SERVER, port=PORT, user=USER, passwd=PASSWORD, db=DB)
    return db


def create(person: Person):
    db = connect()
    cursor = db.cursor()
    sql = f"INSERT INTO person (first_name, last_name, age, sex, income) \
            VALUES ('{person.first_name}', \
                    '{person.last_name}', \
                    '{person.age}', \
                    '{person.sex}', \
                    '{person.income}' )"
    try:
        cursor.execute(sql)
        db.commit()
    except:
        db.rollback()
    finally:
        db.close()


def read(id_person: int) -> Person:
    db = connect()
    cursor = db.cursor()
    sql = "SELECT * FROM PERSON WHERE ID = '%d'" % (id_person)
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        for row in results:
            first_name = row[1]
            last_name = row[2]
            age = row[3]
            sex = row[4]
            income = row[5]
            person = Person(id_person, first_name, last_name, age, sex, income)
    except:
        print("Error fetching data.")
    finally:
        db.close()
    return person


def readAll():
    db = connect()
    cursor = db.cursor()
    sql = "SELECT * FROM PERSON"
    persons = []
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        for row in results:
            id_person = row[0]
            first_name = row[1]
            last_name = row[2]
            age = row[3]
            sex = row[4]
            income = row[5]
            person = Person(id_person, first_name, last_name, age, sex, income)
            persons.append(person)
    except:
        print("Error fetching data.")
    finally:
        db.close()
    return persons


def update(person: Person):
    db = connect()
    cursor = db.cursor()
    sql = "UPDATE PERSON SET FIRST_NAME = '%s', LAST_NAME = '%s', AGE = '%d', \
        SEX = '%c', INCOME = '%d' WHERE ID = '%d'" % \
        (person.first_name, person.last_name, person.age,
         person.sex, person.income, person.id_person)
    try:
        cursor.execute(sql)
        db.commit()
    except:
        db.rollback()
    finally:
        db.close()


def delete(id_person):
    db = connect()
    cursor = db.cursor()
    sql = "DELETE FROM PERSON WHERE ID = '%d'" % (id_person)
    try:
        cursor.execute(sql)
        db.commit()
    except:
        db.rollback()
    finally:
        db.close()
