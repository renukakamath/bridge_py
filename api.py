from flask import * 
from database import* 
import uuid

api=Blueprint('api',__name__)

@api.route('/login')
def login():
	data={}
	u=request.args['username']
	p=request.args['password']
	q1="select * from login where username='%s' and `password`='%s'"%(u,p)
	print(q1)
	res=select(q1)
	if res:
		data['data']=res
		data['status']='success'
	else:
		data['status']='failed'
	return str(data)

@api.route('/userregister')
def userregister():
	data={}
	f=request.args['fname']
	l=request.args['lname']
	
	pl=request.args['place']
	
	ph=request.args['phone']
	e=request.args['email']
	u=request.args['username']
	p=request.args['password']

	q="select * from login where username='%s' and password='%s'"%(u,p)
	res=select(q)
	if res:
		data['status']='already'
	else:
		q="insert into login values(NULL,'%s','%s','user')"%(u,p)
		lid=insert(q)
		r="insert into user values(NULL,'%s','%s','%s','%s','%s','%s')"%(lid,f,l,pl,ph,e)
		insert(r)
		data['status']="success"
	return str(data)



@api.route('/Viewbusiness')
def Viewbusiness():
	data={}
	q="SELECT * FROM  `business`"
	res=select(q)
	data['data']=res
	data['status']="success"
	return str(data)



@api.route('/viewstories')
def viewstories():
	data={}
	bid=request.args['bid']
	q="SELECT * FROM  `stories` inner join business using (business_id) where business_id='%s'"%(bid)
	res=select(q)
	data['data']=res
	data['status']="success"
	return str(data)

@api.route('/Viewnews')
def Viewnews():
	data={}
	
	q="SELECT * FROM  `news` "
	res=select(q)
	data['data']=res
	data['status']="success"
	return str(data)


@api.route('/Postmyideas')
def Postmyideas():
	data={}
	login_id=request.args['login_id']
	name=request.args['name']
	place=request.args['place']
	Phone=request.args['Phone']
	email=request.args['email']
	q="insert into idea values(null,(select user_id from user where login_id='%s'),'%s','%s','%s','%s',curdate(),'pending')"%(login_id,name,place,Phone,email)
	insert(q)
	
	data['status']="success"
	return str(data)

@api.route('/viewproposals')
def viewproposals():
	data={}
	
	q="SELECT * FROM  `proposal` inner join idea using (idea_id) inner join investor using (investor_id) "
	res=select(q)
	data['data']=res
	data['status']="success"
	return str(data)






@api.route('/accept')
def accept():
	data={}
	sid=request.args['sid']
	
	q="update proposal set status='Accept' where proposal_id='%s'"%(sid)
	update(q)
	
	data['status']="success"
	return str(data)

@api.route('/reject')
def reject():
	data={}
	sid=request.args['sid']
	
	q="update login set status='reject' where proposal_id='%s'"%(lid)
	update(q)
	
	data['status']="success"
	return str(data)



@api.route('/Addquery')
def Addquery():
	data={}
	login_id=request.args['login_id']
	query=request.args['query']

	q="insert into query values(null,(select user_id from user where login_id='%s'),'%s',curdate())"%(login_id,query)
	insert(q)
	
	data['status']="success"
	data['method']='Addquery'
	return str(data)


@api.route('/Viewquery')
def Viewquery():
	data={}
	
	q="SELECT * FROM  `query` inner join user using (user_id)  "
	res=select(q)
	data['data']=res
	data['status']="success"
	data['method']="Viewquery"
	return str(data)


@api.route('/Viewsolutions')
def Viewsolutions():
	data={}
	qid=request.args['qid']
	
	q="SELECT * FROM  `solution` inner join query using (query_id) inner join enqyryteam using (enqury_id)  where query_id='%s' "%(qid)
	res=select(q)
	data['data']=res
	data['status']="success"

	return str(data)


@api.route('/viewloandetails')
def viewloandetails():
	data={}
	
	
	q="SELECT * FROM  `loan` inner join banker using (banker_id) "
	res=select(q)
	data['data']=res
	data['status']="success"

	return str(data)


@api.route('/Viewoffers')
def Viewoffers():
	data={}
		
	q="SELECT * FROM  `offers` inner join banker using (banker_id)  "
	res=select(q)
	data['data']=res
	data['status']="success"

	return str(data)








	
