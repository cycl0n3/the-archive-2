CREATE ROLLBACK SEGMENT rbs_one TABLESPACE rbs_ts;

CREATE ROLLBACK SEGMENT rbs_one
   TABLESPACE rbs_ts
   STORAGE
   ( INITIAL 10K
     NEXT 10K
     MAXEXTENTS UNLIMITED );