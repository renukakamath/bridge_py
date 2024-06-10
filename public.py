from flask import * 
from database import*



public=Blueprint('public',__name__)

@public.route('/')
def public_home():

	return render_template('public_home.html')


@public.route('/login',methods=['post','get'])	
def login():
	if "login" in request.form:
		u=request.form['uname']
		pa=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(u,pa)
		res=select(q)
		if res:
			session['login_id']=res[0]['login_id']
			lid=session['login_id']
			if res[0]['usertype']=="admin":
				return redirect(url_for('admin.admin_home'))

			elif res[0]['usertype']=="investor":
				q="select * from investor where login_id='%s'"%(lid)
				res=select(q)
				if res:
					session['investor_id']=res[0]['investor_id']
					iid=session['investor_id']
				return redirect(url_for('investor.investor_home'))

			elif res[0]['usertype']=="bank":
				q="select * from banker where login_id='%s'"%(lid)
				res=select(q)
				if res:
					session['banker_id']=res[0]['banker_id']
					bank=session['banker_id']



				return redirect(url_for('bank.bank_home'))


			elif res[0]['usertype']=="business":
				q="select * from enqyryteam where login_id='%s'"%(lid)
				res=select(q)
				if res:
					session['enqury_id']=res[0]['enqury_id']
					eid=session['enqury_id']



				return redirect(url_for('business.business_home'))


		else:
			flash('invalid username and password')

	return render_template('login.html')


@public.route('/investor_registration',methods=['post','get'])	
def investor_registration():
	if "inv" in request.form:
		f=request.form['fname']
		l=request.form['lname']
		p=request.form['place']
		
		ph=request.form['phone']
		e=request.form['email']
		u=request.form['uname']
		pa=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(u,pa)
		res=select(q)
		if res:

			flash('already exist')

		else:
			
			q="insert into login values(null,'%s','%s','investor')"%(u,pa)
			id=insert(q)
			q="insert into investor values(null,'%s','%s','%s','%s','%s','%s')"%(id,f,l,p,ph,e)
			insert(q)
			flash('successfully')
			return redirect(url_for('public.investor_registration'))

	return render_template('investor_registration.html')


@public.route('/banker_registration',methods=['post','get'])	
def banker_registration():
	if "bunk" in request.form:
		f=request.form['fname']
		
		p=request.form['place']
		
		ph=request.form['phone']
		e=request.form['email']
		u=request.form['uname']
		pa=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(u,pa)
		res=select(q)
		if res:

			flash('already exist')

		else:
			
			q="insert into login values(null,'%s','%s','bank')"%(u,pa)
			id=insert(q)
			q="insert into banker values(null,'%s','%s','%s','%s','%s')"%(id,f,p,ph,e)
			insert(q)
			flash('successfully')
			return redirect(url_for('public.banker_registration'))

	return render_template('banker_registration.html')



@public.route('/business_registration',methods=['post','get'])	
def business_registration():
	if "bus" in request.form:
		f=request.form['fname']
		
		p=request.form['place']
		
		ph=request.form['phone']
	
		u=request.form['uname']
		pa=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(u,pa)
		res=select(q)
		if res:

			flash('already exist')

		else:
			
			q="insert into login values(null,'%s','%s','business')"%(u,pa)
			id=insert(q)
			q="insert into enqyryteam values(null,'%s','%s','%s','%s')"%(id,f,p,ph)
			insert(q)
			flash('successfully')
			return redirect(url_for('public.business_registration'))

	return render_template('business_registration.html')




