from flask import Flask 
from public import public
from admin import admin
from bank import bank
from business import business
from api import api
from investor import investor



app=Flask(__name__)

app.secret_key='key'

app.register_blueprint(public)
app.register_blueprint(admin,url_prefix='/admin')
app.register_blueprint(bank,url_prefix='/bank')
app.register_blueprint(business,url_prefix='/business')
app.register_blueprint(api,url_prefix='/api')
app.register_blueprint(investor,url_prefix='/investor')

app.run(debug=True,port=5039,host="0.0.0.0")