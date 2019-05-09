drop table base;
drop table dept;
drop table accs;
drop table roles;
drop table roles_funs;
drop table funs;
drop table sena;
drop table project;
drop table projectfile;
drop table money;
drop table apply;
drop table step;
drop table stepfile;
drop table report;
drop table item;
drop table itemfile;
drop table water;
drop table accept;
drop table acceptfile;
drop table one;
drop table manc;
drop table four;
drop table seven;
drop table three;
drop table reportfile;
drop sequence enid;

create sequence enid;
/*==============================================================*/
/* Table: base	�����ֵ�,�������ݱ�*/
/*==============================================================*/
create table base(
	bid number(10) primary key,
	pbid number(10),	--����ID�����
	code varchar2(10),	--����
	name varchar2(100),--����
	dex number(2),	--����ֵ
	des varchar2(60)	--����
);
/*==============================================================*/
/* Table: dept	��λ���ű�/
/*==============================================================*/
create table dept(
	did number(10) primary key,
	pdid number(10),	--�ϼ�����ID�����
	tid number(10),	--��λ���ID��������걨��λ�����赥λ��(��/��)ǣͷ���ţ�(��/��)��̬���
	bid number(10),	--��������ID�����
	name varchar2(33),	--��������
	ress varchar2(30),	--���ŵ�ַ
	tel varchar2(30),	--���ŵ绰
	tact varchar2(30),	--�칫����ϵ��
	mob varchar2(30)	--��ϵ�˵绰
);
/*==============================================================*/
/* Table: accs	�˻���*/
/*==============================================================*/
create table accs(
	aid number(10) primary key,
	did number(10),	--����ID�����
	rid number(10),	--��ɫID,���
	username varchar2(20),--��¼��
	password varchar2(20),--��¼����
	sex varchar2(10),	--�Ա�
	tel varchar2(20),	--��ϵ�˵绰
	stat number(1),	--״̬(1���á�0����)
	job varchar2(20),	--ְλ
	name varchar2(20),	--��ʵ����(��ϵ������)
	em varchar2(30),	--��ϵ��EAMIL
	des varchar2(100)	--����
);
/*==============================================================*/
/* Table: roles	��ɫ�� */
/*==============================================================*/
create table roles(
	rid number(10) primary key,
	name varchar2(30),	--��ɫ����
	des varchar2(100)	--����
);
/*==============================================================*/
/* Table: roles_funs	��ɫȨ���м�� */
/*==============================================================*/
create table roles_funs(
	rid number(10),--��ɫID�����
	fid number(10) --Ȩ��ID�����
);
/*==============================================================*/
/* Table: funs	Ȩ�ޱ� */
/*==============================================================*/
create table funs(
	fid number(10) primary key,
	pfid number(10),	--��Ȩ��ID���������
	uri varchar2(100),	--���ʵ�ַ
	des varchar2(100)	--����
);
/*==============================================================*/
/* Table: sena	�걨�˺ű� */
/*==============================================================*/
create table sena(
	sid number(10) primary key,
	unit varchar2(60),	--��λ����
	bid number(10),	--��������,���
	code varchar2(20),	--��λ������
	fade varchar2(20),	--���˴���
	ress varchar2(100),	--��λ��ַ
	tel varchar2(20),	--��λ�绰
	tact varchar2(20),	--��ϵ��
	job varchar2(20),	--��ϵ��ְ��
	mob varchar2(15),	--��ϵ���ֻ�
	em varchar2(30),	--��ϵ��email
	username varchar2(20),--��¼��
	password varchar2(20),--����
  	tim date,	--ע��ʱ��(Ĭ�ϵ�ǰʱ��)
	des varchar2(100),	--��ע
	stat number(1)	--״̬(0δ���ģ�1������)
);

/*==============================================================*/
/* Table: project	��Ŀ��*/
/*==============================================================*/
create table project(
	pid number(10) primary key,
	tid number(10),	--��Ŀ����ID�����
	cid number(10),	--��������ID�����
	fdid number(10),	--��ǣͷ����ID���������
	cdid number(10),	--��ǣͷ����ID���������
	bid number(10),	--���赥λID���������
	vid number(10),	--��Ŀ����ID���������Ϊ�����ؼ���Ŀ���м���Ŀ
	sid number(10),	--����״̬ID,�����δ�����������С������	
	aid number(10),	--���벹��������ID,���
	stat number(10),	--����״̬ID����������ˣ����ˡ���
	kid number(10),	--��Ŀ״̬ID�����
	mary varchar2(100),	--�����ܽ�
	name varchar2(50),	--��Ŀ����
	pno varchar2(20),	--��Ŀ����P2012000001���Ƶĸ�ʽ
	year varchar2(4),	--�ϱ���ȣ����ύ��ȣ��ύʱ�޸�
	ress varchar2(100),	--��Ŀ��ַ
	lat varchar2(15),	--γ��
	lon varchar2(15),	--����
	star date,		--����ʱ��
	en date,		--����ʱ��(����ʱ��)
	total number(15),	--Ͷ���ܽ��
	detail varchar2(100),	--Ͷ�ʹ���˵��
	mon number(15),	--���벹����
	head varchar2(50),	--��Ŀ������
	mob varchar2(50),	--��������ϵ�绰
	des varchar2(255)	--��Ŀ����
);
/*==============================================================*/
/* Table: projectfile	��Ŀ������*/
/*==============================================================*/
create table projectfile(
	pfid number(10) primary key,
	pid number(10),	--��ĿID�����
	fn varchar2(50),	--�ļ�����
	txt blob,		--�ļ�����
	tp varchar2(30),	--�ļ�����
	siz number(20),	--�ļ���С
	tim date		--�ϴ�ʱ��
);
/*==============================================================*/
/* Table: money	��Ŀ�ʽ��  */
/*==============================================================*/
create table money(
	pid number(10) primary key,--��ĿID�����(1��1)
	total number(15),	--�ܽ��
	cou number(15),	--���Ҳ���
	pro number(15),	--ʡ����
	city number(15),	--�в���
	dist number(15),	--�ز���
	com number(15)	--��ҵ�����ʽ�
);
/*==============================================================*/
/* Table: apply	��Ŀ�걨��*/
/*==============================================================*/
create table apply(
	pid number(10) primary key,--��ĿID�����(1��1)
	aaid number(10),	--�걨��ID�����
	did number(10),	--�걨����������ID�����
	lastid number(10),	--�����������ID�����
	curdid number(10),	--��ǰ������ID����� 79
	wid number(10),	--ˮ�ʱ���ID�����
	tim date		--�걨����
);
/*==============================================================*/
/* Table: step	��Ŀ�������ڱ�*/
/*==============================================================*/
create table step(
	sid number(10) primary key,
	pid number(10),	--��ĿID�����*
	bid number(10),	--�������ID��������������ݣ���ͨ�����˻ء���ֹ���������
	aid number(10),	--��ǰ������ID��������������
	did number(10),	--��ǰ��������ID�����*79
	nid number(10),	--��һ��������ID������������ͨ������78
	tim date,		--����ʱ�䣬�������
	txt varchar2(200),	--����������������
	tid number(1)	--������������(0���������ڵ㣬1���������ڵ�)*
);
/*==============================================================*/
/* Table: stepfile	�������ڸ�����*/
/*==============================================================*/
create table stepfile(
	sfid number(10) primary key,
	sid number(10),	--��Ŀ��������ID�����
	pid number(10),	--��ĿID�����
	fn varchar2(50),	--�ļ�����
	txt blob,		--�ļ�����
	tp varchar2(30),	--�ļ�����
	siz number(20),	--�ļ���С
	tim date		--�ϴ�ʱ��
);
/*==============================================================*/
/* Table: report	��Ŀ���ȱ����*/
/*==============================================================*/
create table report(
	rid number(10) primary key,
	pid number(10),	--��ĿID�����
	aid number(10),	--�ϱ���ID�����
	did number(10),	--�ϱ�����������ID�����
	wid number(10),	--ˮ�ʱ���ID�����
	tid number(10),	--��������ID�������δ�����������У������
	retim date,	--�ϱ�ʱ�䣬��ʽ��yyyy-MM
	des varchar2(255),	--����
	rea varchar2(100),	--δ����ԭ�����typeΪ"δ����"�����������
	state number(10),	--������ȣ����typeΪ"������"�������������С��������豸��װ�����������ֽ׶�
	num number,	--���������ɰٷֱȣ�����51.50��������������
   	fintim date	--��Ŀ���ʱ�䣬����û�ѡ��typeΪ"�����"�����������
);
/*==============================================================*/
/* Table: reportfile	��Ŀ���ȱ��渽����*/
/*==============================================================*/
create table reportfile(
       rfid number(10) primary key,
       rid number(10),
       pid number(10),
       fn varchar2(100),
       txt blob,
       tp varchar2(50),
       siz number(20),
       tim date
);
/*==============================================================*/
/* Table: item	��Ŀ�����*/
/*==============================================================*/
create table item(
	iid number(10) primary key,
	pid number(10),	--��ĿID�����
	wid number(10),	--ˮ�ʱ���ID�����
	tact varchar2(20),	--�ϱ���
	dist varchar2(100),	--�ϱ�����ϵ��ʽ
	dname varchar2(50),--�ϱ�����������
	tim date,		--��������
	txt varchar2(255)	--�������
);
/*==============================================================*/
/* Table: itemfile	���鸽���� */
/*==============================================================*/
create table itemfile(
	ifid number(10) primary key,
	iid number(10),	--��Ŀ����ID�����
	pid number(10),	--��ĿID�����
	fn varchar2(50),	--�ļ�����
	txt blob,		--�ļ�����
	tp varchar2(30),	--�ļ�����(��չ��)
	siz number(20),	--�ļ���С
	tim date		--�ϴ�ʱ��
);
/*==============================================================*/
/* Table: water	ˮ�ʱ����  */
/*==============================================================*/
create table water(
	wid number(10) primary key,
	pid number(10),	--��ĿID�����
	cod number,	--CODָ��
	am number,	--��������
	tp number,	--����ֵ
	ph number,	--PHֵ
	des varchar2(255),	--��ע
	tim date		--�ɼ�ʱ��
);
/*==============================================================*/
/* Table: accept	��Ŀ���ձ�*/
/*==============================================================*/
create table accept(
	pid number(10) primary key,--��ĿID�����(1��1)
	aid number(10),	--������ID�����
	did number(10),	--���ղ���ID�����
	wid number(10),	--ˮ�ʱ���ID�����
	stat number(10),	--��Ŀ����״̬ID����������顢�����
	note varchar2(255),	--����˵��
	tim date,		--����ʱ�䣬������ʱ��
	tel varchar2(15),	--�����˵绰
	per varchar2(20),	--��Ŀ������
	noe varchar2(255),	--���ձ���
	work varchar2(255),	--��������
	res varchar2(255)	--��Ŀ���ս���
);
/*==============================================================*/
/* Table: acceptfile	���ո�����*/
/*==============================================================*/
create table acceptfile(
	afid number(10) primary key,
	pid number(10),	--��Ŀ����ID�����
	fn varchar2(50),	--�ļ�����
	txt blob,		--�ļ�����
	tp varchar2(30),	--�ļ�����
	siz number(20),	--�ļ���С
	tim date		--�ϴ�ʱ��
);



