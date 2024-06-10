from flask import * 
from database import*



business=Blueprint('business',__name__)

@business.route('/business_home')
def business_home():

	return render_template('business_home.html')


@business.route('/business_viewquery')
def business_viewquery():
	data={}
	q="SELECT * FROM  `query` INNER JOIN `user` USING (user_id) "
	res=select(q)
	data['query']=res


	return render_template('business_viewquery.html',data=data)

@business.route('/business_postsolution',methods=['post','get'])	
def business_postsolution():
	data={}
	eid=session['enqury_id']
	q="select * from solution   where enqury_id='%s'"%(eid)
	res=select(q)
	data['cust']=res



	if "Solution" in request.form:
		a=request.form['fname']
		qid=request.args['qid']

		
		q="insert into solution values(null,'%s','%s','%s')"%(qid,a,eid)
		insert(q)

		flash('successfully')
		return redirect(url_for('business.business_postsolution'))
	
	return render_template('business_postsolution.html',data=data)