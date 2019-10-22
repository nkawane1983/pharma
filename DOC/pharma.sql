
CREATE schema "pharma";


CREATE TABLE "pharma"."app_user" 
(	"usr_id" character varying(30) NOT NULL, 
	"usr_name" character varying(30) NOT NULL, 
	"usr_passwd" character varying(255) NOT NULL, 
	"usr_display_name" character varying(255), 
	"usr_first_name" character varying(50), 
	"usr_middle_name" character varying(50), 
	"usr_last_name" character varying(50), 
	"usr_telephone" character varying(30), 
	"usr_mobile" character varying(30), 
	"usr_fax" character varying(30), 
	"usr_email" character varying(30), 
	"usr_isactive_yn" character varying(1) DEFAULT 'Y', 
	"usr_employee_yn" character varying(1) DEFAULT 'Y', 
	"usr_lock_yn" character varying(1) DEFAULT 'N', 
	"usr_expiry_dt" DATE, 
	"usr_type" character varying(20), 
	"usr_last_login" DATE, 
	"usr_passwd_history" character varying(2000), 
	"usr_passwd_expiration_dt" DATE, 
	--"USR_SIGNATURE" BLOB, 
	"usr_designation" character varying(100), 
	"usr_department_cd" character varying(15), 
	"created_by" character varying(30) DEFAULT user, 
	"created_dt" DATE, 
	"updated_by" character varying(30), 
	"updated_dt" DATE,
	CONSTRAINT app_user_pkey PRIMARY KEY (usr_id)
) ;
	 
	 
insert into pharma.app_user (usr_id, usr_name, usr_passwd) values ('nish', 'nish', '$2a$10$lcRfJH0Q.f6GW6BGVWZwn.2mFv5lWjg5Tszfok6IpfyB823vA8mue');

create table pharma.user_type(
		id serial NOT NULL,
		name character varying(30),
		description character varying(100),
		created_by character varying(30) NOT NULL DEFAULT "current_user"(),
		created_date timestamp without time zone NOT NULL DEFAULT now(),
		updated_by character varying(30),
		updated_date timestamp without time zone,
		CONSTRAINT user_type_key PRIMARY KEY (id)
)

CREATE TABLE "pharma"."role" 
 (	"rol_id" serial NOT NULL,
	"rol_name" character varying(30 ), 
	"rol_description" character varying(100 ), 
	"rol_isactive" character varying(1 ) DEFAULT 'Y', 
	"rol_parent_id" integer NOT NULL, 
	 CONSTRAINT "pk_role" PRIMARY KEY ("rol_id")
)

CREATE TABLE "pharma"."user_type_role" 
(	
	"usr_type_id" integer NOT NULL, 
	"role_id" integer NOT NULL, 
	 CONSTRAINT "pk_user_type_role" PRIMARY KEY ("usr_type_id","role_id")
)
	 
CREATE TABLE "pharma"."persistent_logins" 
(	"USERNAME" character varying(64) NOT NULL , 
	"SERIES" character varying(64) NOT NULL , 
	"TOKEN" character varying(64) NOT NULL , 
	"LAST_USED" TIMESTAMP (6) NOT NULL , 
	 PRIMARY KEY ("SERIES")
 
)

CREATE TABLE pharma.system_parameter
(
  id serial NOT NULL,
  parameter_name character varying(50) not null,
  parameter_value character varying(50) not null,
  description character varying(100),
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone,
  CONSTRAINT pk_system_parameter PRIMARY KEY (id)
)
CREATE TABLE pharma.branch
(
  id serial NOT NULL,
  branch_name character varying(50) NOT NULL,
  branch_manager character varying(50) NOT NULL,
  address character varying(100),
  branch_telephone character varying(30),
  branch_mobile character varying(30),
  branch_email character varying(30),
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone,
  CONSTRAINT pk_branch PRIMARY KEY (id)
)



















--	***************************************
--		new tables
--	***************************************


CREATE TABLE pharma.banking
(
  id serial NOT NULL,
  branch_id integer,
  date timestamp without time zone NOT NULL DEFAULT now(),
  banking_ref character varying(30),
  cash double precision,
  cheques double precision,
  user_id character varying(10),
  misc_cash double precision,
  company_id integer,
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT banking_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.banking OWNER TO postgres;
















CREATE TABLE pharma.cash_summary
(
  id serial NOT NULL,
  branch_id integer,
  --banking_id integer,
  --company_id integer,
  
  date timestamp without time zone NOT NULL DEFAULT now(),
  till_no integer,
  ref_no character varying(15),
  z_reading double precision,
  voids double precision,
  refunds double precision,
  sales integer,
  cash double precision,
  cheques double precision,
  cards double precision,
  coupuns double precision,
  paidouts double precision,
  --d_double precision double precision,

  nett0 double precision,
  nett1 double precision,
  nett2 double precision,
  nett3 double precision,

  vat0 double precision,
  vat1 double precision,
  vat2 double precision,
  vat3 double precision,

  --note character varying(100),
  --manager character varying(10),
  user_id character varying(10),
  misc_cash double precision,

  z_read_private_value double precision,
  z_read_levy double precision,

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),

  CONSTRAINT cash_summary_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.cash_summary OWNER TO postgres;
   



CREATE TABLE pharma.paid_outs
(
  id serial NOT NULL,
  branch_id integer,
  cash_id integer,
  type integer,
  amount double precision,
  company_id integer,
  description character varying(10),
  vat double precision,

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),

  CONSTRAINT paid_outs_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.paid_outs OWNER TO postgres;





CREATE TABLE pharma.takings_cards
(
  id serial NOT NULL,
  branch_id integer,
  cash_id integer,
  type integer,
  amount double precision,
  banking_id integer,
  company_id integer,
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),

  CONSTRAINT takings_cards_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.takings_cards OWNER TO postgres;





CREATE TABLE pharma.takings_cash
(
  id serial NOT NULL,
  branch_id integer,
  cash_id integer,
  quantity integer,
  denomination_id integer,
  company_id integer,
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),

  CONSTRAINT takings_cash_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.takings_cash OWNER TO postgres;





CREATE TABLE pharma.denominations
(
  id serial NOT NULL,
  denominations character varying(50),
  multiplier double precision,
  quantity double precision,
  amount integer,
  display_order integer,
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),

  CONSTRAINT denominations_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.denominations OWNER TO postgres;






CREATE TABLE pharma.takings_cheques
(
  id serial NOT NULL,
  branch_id integer,
  cash_id integer,
  name character varying(50),
  amount double precision,
  banking_id integer,
  company_id integer,
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),

  CONSTRAINT takings_cheques_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.takings_cheques OWNER TO postgres;







CREATE TABLE pharma.takings_coupons
(
  id serial NOT NULL,
  branch_id integer,
  cash_id integer,
  type integer,
  amount double precision,
  company_id integer,
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),

  CONSTRAINT takings_coupons_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.takings_coupons OWNER TO postgres;




CREATE TABLE pharma.takings_misc_cash
(
  id serial NOT NULL,
  branch_id integer,
  cash_id integer,
  type integer,
  amount double precision,
  company_id integer,
  description character varying(50),
  ref character varying(20),
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),

  CONSTRAINT takings_misc_cash_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.takings_misc_cash OWNER TO postgres;


















CREATE TABLE pharma.nhs
(
  id serial NOT NULL,
  branch_id integer,
  date timestamp without time zone NOT NULL DEFAULT now(),
  paid_form integer,
  paid_item integer,
  no_charge_item integer,
  prescription_charge double precision,
  paid_form_old integer,
  paid_item_old integer,
  no_charge_item_old integer,
  prescription_charge_old double precision,
  exempt_form integer,
  exempt_item integer,
  contraceptive_forms integer,
  contraceptive_items integer,
  private_prescription_items integer,
  rivate_prescription_value double precision,
  other_prescription_items integer,
  other_prescription_value double precision,
  other_prescription_vat double precision,
  ncso_items integer,
  user_id character varying(10),
  company_id integer,
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT nhs_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.nhs OWNER TO postgres;




CREATE TABLE pharma.nhs_items
(
  id serial NOT NULL,
  branch_id integer,
  date timestamp without time zone NOT NULL DEFAULT now(),
  category_id integer,
  patients integer,
  forms integer,
  items integer,
  company_id integer,
    
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),

  CONSTRAINT nhs_items_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.nhs_items OWNER TO postgres;




CREATE TABLE pharma.re_subs
(
  id serial NOT NULL,
  branch_id integer,
  date timestamp without time zone NOT NULL DEFAULT now(),
  paid_form integer,
  paid_item integer,
  exempt_form integer,
  exempt_item integer,
  contraceptive_forms integer,
  contraceptive_items integer,
  no_charge_item_old integer,

  user_id character varying(10),
  company_id integer,

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT re_subs_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.re_subs OWNER TO postgres;

















CREATE TABLE pharma.care_homes
(
  id serial NOT NULL,
  branch_id integer,
  description character varying(10),
  address_id integer,
  company_id integer,

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT care_homes_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.care_homes OWNER TO postgres;




CREATE TABLE pharma.script_items
(
  id serial NOT NULL,
  item_id integer,
  date timestamp without time zone NOT NULL DEFAULT now(),
  category_id integer,
  description character varying(10),
  forms integer,
  items integer,
  company_id integer,

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT script_items_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.script_items OWNER TO postgres;





















CREATE TABLE pharma.branches
(
  id serial NOT NULL,
  branch_number character varying(10),
  branch_name character varying(50),
  manager character varying(50),
  address_id integer,
  phone character varying(20),
  fax character varying(20),
  email character varying(100),
  manager_mobile character varying(20),

  imsn_number character varying(10),

  notes character varying(100),
  no_of_tills integer,
  user_id character varying(10),
  company_id integer,

  version_no character varying(10),
  pharm_cash_version character varying(10),
  pharm_time_version character varying(10),
  pharm_clinical_version character varying(10),
  
  period integer,
  cd_monthly_target integer,
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT branches_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.branches OWNER TO postgres;



























--  UserGroups   rename to user_type

CREATE TABLE pharma.user_type
(
  id serial NOT NULL,
  name character varying(30),
  description character varying(100),
  --allow_add boolean,
  --allow_edit boolean,
  --allow_delete boolean,
  --allow_print boolean,
  --allow_import boolean,
  --allow_export boolean,
  --administrator boolean,

  user_id integer,
  company_id integer,
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT user_type_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.user_type OWNER TO postgres;








CREATE TABLE pharma.users
(
  id serial NOT NULL,
  name character varying(40),
  password character varying(100),
  --administrator bit,
  user_id character varying(10),
  company_id integer,
  unique_id integer,

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.users OWNER TO postgres;

























CREATE TABLE pharma.collection_n_deliverty
(
  id serial NOT NULL,
  --sign_up_id integer,
  branch_id integer,
  date timestamp without time zone NOT NULL DEFAULT now(),
  user_id character varying(10),
  
  coll_items integer,
  delv_items integer,
  company_id integer,

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT collection_n_deliverty_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.collection_n_deliverty OWNER TO postgres;
























-- CategoriesMaster rename to sys_category

CREATE TABLE pharma.sys_category
(
  id serial NOT NULL,
  name character varying(50),
  "group" character varying(10),
  --debit_or_credit character varying(1),
  amount double precision,
  display_order int,
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT sys_category_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.sys_category OWNER TO postgres;






















CREATE TABLE pharma.group
(
  id serial NOT NULL,
  name character varying(40),
  company_id integer,

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT group_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.group OWNER TO postgres;



CREATE TABLE pharma.group_user
(
  id serial NOT NULL,
  group_id integer,
  user_id integer,
  company_id integer,

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT group_user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.group_user OWNER TO postgres;






CREATE TABLE pharma.group_branch
(
  id serial NOT NULL,
  group_id integer,
  branch_id integer,
  company_id integer,

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT group_branch_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.group_branch OWNER TO postgres;














CREATE TABLE pharma.module
(
  id serial NOT NULL,
  name character varying(50),
  database_name character varying(50),

  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT module_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.module OWNER TO postgres;














CREATE TABLE pharma.company
(
  id serial NOT NULL,
  name character varying(50),
  address_id integer,
  contact character varying(50),
  phone character varying(20),
  fax character varying(20),
  email character varying(50),
  note character varying(256),
  
  group_login_name character varying(50),
  group_password character varying(50),
  url character varying(50),
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT sys_company_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.company OWNER TO postgres;







CREATE TABLE pharma.company_module
(
  id serial NOT NULL,
  company_id integer,
  module_id integer,
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT company_module_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.company_module OWNER TO postgres;











-- DataExemptDates   rename to holiday

CREATE TABLE pharma.holiday
(
  id serial NOT NULL,
  date timestamp without time zone NOT NULL DEFAULT now(),
  description character varying(50),
  recurring boolean,
  company_id integer,
  
  created_by character varying(30) NOT NULL DEFAULT "current_user"(),
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  updated_by character varying(30),
  updated_date timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT holiday_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pharma.holiday OWNER TO postgres;









-- MonthlyExpenses(VIEW)
-- vw_CashSummaryPaidOuts(VIEW)