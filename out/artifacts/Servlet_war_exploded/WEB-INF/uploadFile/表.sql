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
/* Table: base	数据字典,基础数据表*/
/*==============================================================*/
create table base(
	bid number(10) primary key,
	pbid number(10),	--父项ID，外键
	code varchar2(10),	--编码
	name varchar2(100),--名称
	dex number(2),	--排序值
	des varchar2(60)	--描述
);
/*==============================================================*/
/* Table: dept	单位部门表/
/*==============================================================*/
create table dept(
	did number(10) primary key,
	pdid number(10),	--上级部门ID，外键
	tid number(10),	--单位类别ID，外键，申报单位、建设单位，(县/市)牵头部门，(县/市)生态办等
	bid number(10),	--所属区县ID，外键
	name varchar2(33),	--部门名称
	ress varchar2(30),	--部门地址
	tel varchar2(30),	--部门电话
	tact varchar2(30),	--办公室联系人
	mob varchar2(30)	--联系人电话
);
/*==============================================================*/
/* Table: accs	账户表*/
/*==============================================================*/
create table accs(
	aid number(10) primary key,
	did number(10),	--部门ID，外键
	rid number(10),	--角色ID,外键
	username varchar2(20),--登录名
	password varchar2(20),--登录密码
	sex varchar2(10),	--性别
	tel varchar2(20),	--联系人电话
	stat number(1),	--状态(1启用、0禁用)
	job varchar2(20),	--职位
	name varchar2(20),	--真实姓名(联系人姓名)
	em varchar2(30),	--联系人EAMIL
	des varchar2(100)	--描述
);
/*==============================================================*/
/* Table: roles	角色表 */
/*==============================================================*/
create table roles(
	rid number(10) primary key,
	name varchar2(30),	--角色名称
	des varchar2(100)	--描述
);
/*==============================================================*/
/* Table: roles_funs	角色权限中间表 */
/*==============================================================*/
create table roles_funs(
	rid number(10),--角色ID，外键
	fid number(10) --权限ID，外键
);
/*==============================================================*/
/* Table: funs	权限表 */
/*==============================================================*/
create table funs(
	fid number(10) primary key,
	pfid number(10),	--父权限ID，自身外键
	uri varchar2(100),	--访问地址
	des varchar2(100)	--描述
);
/*==============================================================*/
/* Table: sena	申报账号表 */
/*==============================================================*/
create table sena(
	sid number(10) primary key,
	unit varchar2(60),	--单位名称
	bid number(10),	--所属区划,外键
	code varchar2(20),	--单位机构码
	fade varchar2(20),	--法人代表
	ress varchar2(100),	--单位地址
	tel varchar2(20),	--单位电话
	tact varchar2(20),	--联系人
	job varchar2(20),	--联系人职务
	mob varchar2(15),	--联系人手机
	em varchar2(30),	--联系人email
	username varchar2(20),--登录名
	password varchar2(20),--密码
  	tim date,	--注册时间(默认当前时间)
	des varchar2(100),	--备注
	stat number(1)	--状态(0未审阅，1已审阅)
);

/*==============================================================*/
/* Table: project	项目表*/
/*==============================================================*/
create table project(
	pid number(10) primary key,
	tid number(10),	--项目类型ID，外键
	cid number(10),	--所属区县ID，外键
	fdid number(10),	--县牵头部门ID，部门外键
	cdid number(10),	--市牵头部门ID，部门外键
	bid number(10),	--建设单位ID，部门外键
	vid number(10),	--项目级别ID，外键，分为：区县级项目，市级项目
	sid number(10),	--跟踪状态ID,外键：未启动、建设中、已完成	
	aid number(10),	--申请补助金类型ID,外键
	stat number(10),	--审批状态ID，外键，初核，复核……
	kid number(10),	--项目状态ID，外键
	mary varchar2(100),	--工作总结
	name varchar2(50),	--项目名称
	pno varchar2(20),	--项目编码P2012000001类似的格式
	year varchar2(4),	--上报年度，即提交年度，提交时修改
	ress varchar2(100),	--项目地址
	lat varchar2(15),	--纬度
	lon varchar2(15),	--经度
	star date,		--开工时间
	en date,		--结束时间(验收时间)
	total number(15),	--投资总金额
	detail varchar2(100),	--投资构成说明
	mon number(15),	--申请补助金
	head varchar2(50),	--项目负责人
	mob varchar2(50),	--负责人联系电话
	des varchar2(255)	--项目概述
);
/*==============================================================*/
/* Table: projectfile	项目附件表*/
/*==============================================================*/
create table projectfile(
	pfid number(10) primary key,
	pid number(10),	--项目ID，外键
	fn varchar2(50),	--文件名称
	txt blob,		--文件对象
	tp varchar2(30),	--文件类型
	siz number(20),	--文件大小
	tim date		--上传时间
);
/*==============================================================*/
/* Table: money	项目资金表  */
/*==============================================================*/
create table money(
	pid number(10) primary key,--项目ID，外键(1对1)
	total number(15),	--总金额
	cou number(15),	--国家拨款
	pro number(15),	--省拨款
	city number(15),	--市拨款
	dist number(15),	--县拨款
	com number(15)	--企业配套资金
);
/*==============================================================*/
/* Table: apply	项目申报表*/
/*==============================================================*/
create table apply(
	pid number(10) primary key,--项目ID，外键(1对1)
	aaid number(10),	--申报人ID，外键
	did number(10),	--申报人所属部门ID，外键
	lastid number(10),	--最后审批部门ID，外键
	curdid number(10),	--当前待审部门ID，外键 79
	wid number(10),	--水质报告ID，外键
	tim date		--申报日期
);
/*==============================================================*/
/* Table: step	项目审批环节表*/
/*==============================================================*/
create table step(
	sid number(10) primary key,
	pid number(10),	--项目ID，外键*
	bid number(10),	--审批结果ID，外键。基础数据，如通过、退回、终止，审过才有
	aid number(10),	--当前审批人ID，外键，审过才有
	did number(10),	--当前审批部门ID，外键*79
	nid number(10),	--下一审批部门ID，外键，审过并通过才有78
	tim date,		--审批时间，审过才有
	txt varchar2(200),	--审批意见，审过才有
	tid number(1)	--审批环节类型(0立项审批节点，1验收审批节点)*
);
/*==============================================================*/
/* Table: stepfile	审批环节附件表*/
/*==============================================================*/
create table stepfile(
	sfid number(10) primary key,
	sid number(10),	--项目审批环节ID，外键
	pid number(10),	--项目ID，外键
	fn varchar2(50),	--文件名称
	txt blob,		--文件对象
	tp varchar2(30),	--文件类型
	siz number(20),	--文件大小
	tim date		--上传时间
);
/*==============================================================*/
/* Table: report	项目进度报告表*/
/*==============================================================*/
create table report(
	rid number(10) primary key,
	pid number(10),	--项目ID，外键
	aid number(10),	--上报人ID，外键
	did number(10),	--上报人所属部门ID，外键
	wid number(10),	--水质报告ID，外键
	tid number(10),	--进度类型ID，外键：未启动，建设中，已完成
	retim date,	--上报时间，格式：yyyy-MM
	des varchar2(255),	--概述
	rea varchar2(100),	--未启动原因，如果type为"未启动"，则填入该项
	state number(10),	--建设进度，如果type为"建设中"，则填入该项：可研、土建、设备安装、试运行四种阶段
	num number,	--建设进度完成百分比，输入51.50类似这样的数字
   	fintim date	--项目完成时间，如果用户选择type为"已完成"，则填入该项
);
/*==============================================================*/
/* Table: reportfile	项目进度报告附件表*/
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
/* Table: item	项目督查表*/
/*==============================================================*/
create table item(
	iid number(10) primary key,
	pid number(10),	--项目ID，外键
	wid number(10),	--水质报告ID，外键
	tact varchar2(20),	--上报人
	dist varchar2(100),	--上报人联系方式
	dname varchar2(50),--上报人所属部门
	tim date,		--督查日期
	txt varchar2(255)	--督查意见
);
/*==============================================================*/
/* Table: itemfile	督查附件表 */
/*==============================================================*/
create table itemfile(
	ifid number(10) primary key,
	iid number(10),	--项目督查ID，外键
	pid number(10),	--项目ID，外键
	fn varchar2(50),	--文件名称
	txt blob,		--文件对象
	tp varchar2(30),	--文件类型(扩展名)
	siz number(20),	--文件大小
	tim date		--上传时间
);
/*==============================================================*/
/* Table: water	水质报告表  */
/*==============================================================*/
create table water(
	wid number(10) primary key,
	pid number(10),	--项目ID，外键
	cod number,	--COD指标
	am number,	--氨氮含量
	tp number,	--总磷值
	ph number,	--PH值
	des varchar2(255),	--备注
	tim date		--采集时间
);
/*==============================================================*/
/* Table: accept	项目验收表*/
/*==============================================================*/
create table accept(
	pid number(10) primary key,--项目ID，外键(1对1)
	aid number(10),	--验收人ID，外键
	did number(10),	--验收部门ID，外键
	wid number(10),	--水质报告ID，外键
	stat number(10),	--项目验收状态ID，外键：初验、终验等
	note varchar2(255),	--验收说明
	tim date,		--创建时间，即验收时间
	tel varchar2(15),	--验收人电话
	per varchar2(20),	--项目负责人
	noe varchar2(255),	--验收报告
	work varchar2(255),	--工作报告
	res varchar2(255)	--项目验收结论
);
/*==============================================================*/
/* Table: acceptfile	验收附件表*/
/*==============================================================*/
create table acceptfile(
	afid number(10) primary key,
	pid number(10),	--项目验收ID，外键
	fn varchar2(50),	--文件名称
	txt blob,		--文件对象
	tp varchar2(30),	--文件类型
	siz number(20),	--文件大小
	tim date		--上传时间
);



