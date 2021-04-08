from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from config import cfg

DIALECT = cfg['dialect']
CONNECTOR = cfg['connector']
HOST = cfg['host']
PORT = cfg['port']
USER = cfg['user']
PWD = cfg['pwd']
DB = cfg['db']

# TODO Empty password from yaml
engine = create_engine(f'{DIALECT}+{CONNECTOR}://{USER}:{PWD}@{HOST}:{PORT}/{DB}')
Session = sessionmaker(bind=engine)

Base = declarative_base()
