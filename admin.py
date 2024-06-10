from flask import * 
from database import*
import uuid



admin=Blueprint('admin',__name__)

@admin.route('/admin_home')
def admin_home():

	return render_template('admin_home.html')


@admin.route('/admin_managenews',methods=['post','get'])	
def admin_managenews():
	data={}
	q="select * from news"
	res=select(q)
	data['newsview']=res

	if "action" in request.args:
		action=request.args['action']
		vid=request.args['vid']

	else:
		action=None

	if action=='delete':
		q="delete from news where news_id='%s' "%(vid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managenews'))

	if action=='update':
		q="select * from news where news_id='%s'"%(vid)
		res=select(q)
		data['newsup']=res
		


	if "update" in request.form:
		t=request.form['news']
		q="update news set news='%s' where news_id='%s' "%(t,vid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managenews'))


	if "addnews" in request.form:
		t=request.form['news']
		q="insert into news values(null,'%s')"%(t)
		insert(q)

		flash('successfully')
		return redirect(url_for('admin.admin_managenews'))
	
	return render_template('admin_managenews.html',data=data)


@admin.route('/admin_managebusiness',methods=['post','get'])	
def admin_managebusiness():
	data={}
	q="select * from business"
	res=select(q)
	data['businessview']=res




	if "action" in request.args:
		action=request.args['action']
		bid=request.args['bid']

	else:
		action=None

	if action=='delete':
		q="delete from business where business_id='%s' "%(bid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managebusiness'))

	if action=='update':
		q="select * from business where business_id='%s'"%(bid)
		res=select(q)
		data['businessup']=res
		


	if "update" in request.form:
		f=request.form['fname']
		d=request.form['details']
		da=request.form['date']
		q="update business set name='%s',details='%s',date='%s' where business_id='%s' "%(f,d,da,bid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managebusiness'))


	if "admin" in request.form:
		f=request.form['fname']
		d=request.form['details']
		da=request.form['date']
		q="insert into business values(null,'%s','%s','%s')"%(f,d,da)
		insert(q)

		flash('successfully')
		return redirect(url_for('admin.admin_managebusiness'))
	
	return render_template('admin_managebusiness.html',data=data)

@admin.route('/admin_viewideas')
def admin_viewideas():
	data={}
	q="SELECT * FROM  `idea` INNER JOIN `user` USING (user_id) "
	res=select(q)
	data['idea']=res


	return render_template('admin_viewideas.html',data=data)

@admin.route('/admin_managestories' ,methods=['post','get'])
def admin_managestories():
	data={}
	q="SELECT * FROM  `stories` INNER JOIN `business` USING (business_id) "
	res=select(q)
	data['stories']=res



	if "action" in request.args:
		action=request.args['action']
		bid=request.args['sid']

	else:
		action=None

	if action=='delete':
		q="delete from stories where stories_id='%s' "%(bid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managestories'))

	if action=='update':
		q="select * from stories where stories_id='%s'"%(bid)
		res=select(q)
		data['storyup']=res
		


	if "update" in request.form:
		d=request.form['details']
		i=request.files['image']
		path="static/image"+str(uuid.uuid4())+i.filename
		i.save(path)
		
		q="update stories set details='%s',image='%s' where stories_id='%s' "%(d,path,bid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managestories'))



	if "imagesss" in request.form:
		d=request.form['details']
		i=request.files['image']
		path="static/image"+str(uuid.uuid4())+i.filename
		i.save(path)
		bid=request.args['bid']
		q="insert into stories values(null,'%s','%s','%s')"%(bid,path,d)
		insert(q)
		return redirect(url_for('admin.admin_managestories'))


	return render_template('admin_managestories.html',data=data)