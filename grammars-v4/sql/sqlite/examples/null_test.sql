CREATE TABLE "Person" (
"PersonId"	    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
"GivenName"	    NVARCHAR(255) NOT NULL,
"FamilyName"	NVARCHAR(255) NULL, -- #2915
"Deleted"	    INTEGER DEFAULT NULL
)
