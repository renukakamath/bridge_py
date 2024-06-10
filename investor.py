from flask import * 
from database import*



investor=Blueprint('investor',__name__)

@investor.route('/investor_home')
def investor_home():

	return render_template('investor_home.html')


@investor.route('/investor_viewideas')
def investor_viewideas():
	data={}
	q="SELECT * FROM  `idea` INNER JOIN `user` USING (user_id) "
	res=select(q)
	data['idea']=res


	return render_template('investor_viewideas.html',data=data)

@investor.route('/investor_viewuser')
def investor_viewuser():
	data={}
	q="SELECT * FROM  `user`"
	res=select(q)
	data['userss']=res


	return render_template('investor_viewuser.html',data=data)

@investor.route('/investor_sendproposal',methods=['post','get'])	
def investor_sendproposal():
	data={}
	iid=session['investor_id']
	# q="select * from proposal inner join idea using (idea_id) where investor_id='%s'"%(iid)
	# res=select(q)
	# data['varientview']=res



	if "sendproposal" in request.form:
		a=request.form['Amount']
		il=request.args['iid']
		
		q="insert into proposal values(null,'%s','%s','%s',curdate(),'pending')"%(il,iid,a)
		insert(q)

		flash('successfully')
		return redirect(url_for('investor.investor_sendproposal'))
	
	return render_template('investor_sendproposal.html',data=data)
@investor.route('/investor_viewproposal',methods=['post','get'])	
def investor_viewproposal():
	data={}
	iid=session['investor_id']
	q="select * from proposal inner join idea using (idea_id) where investor_id='%s'"%(iid)
	res=select(q)
	data['varientview']=res



	
	return render_template('investor_viewproposal.html',data=data)