from flask import * 
from database import*
import uuid



bank=Blueprint('bank',__name__)

@bank.route('/bank_home')
def bank_home():

	return render_template('bank_home.html')

@bank.route('/banker_addloan',methods=['post','get'])	
def banker_addloan():
	data={}
	iid=session['banker_id']
	q="select * from loan   where banker_id='%s'"%(iid)
	res=select(q)
	data['varientview']=res



	if "add" in request.form:
		iid=session['banker_id']
		a=request.form['details']
		il=request.form['days']
		am=request.form['Amount']
		ma=request.form['mAmount']
		i=request.files['img']
		path="static/image"+str(uuid.uuid4())+i.filename
		i.save(path)

		
		q="insert into loan values(null,'%s','%s','%s','%s','%s','%s')"%(iid,a,il,am,ma,path)
		insert(q)

		flash('successfully')
		return redirect(url_for('bank.banker_addloan'))

	return render_template('banker_addloan.html',data=data)

@bank.route('/bank_addoffers',methods=['post','get'])	
def bank_addoffers():
	data={}
	iid=session['banker_id']
	q="select * from offers   where banker_id='%s'"%(iid)
	res=select(q)
	data['varientview']=res



	if "add" in request.form:
		iid=session['banker_id']
		a=request.form['details']
		il=request.form['days']
		

		
		q="insert into offers values(null,'%s','%s','%s')"%(iid,a,il)
		insert(q)

		flash('successfully')
		return redirect(url_for('bank.bank_addoffers'))

	return render_template('bank_addoffers.html',data=data)