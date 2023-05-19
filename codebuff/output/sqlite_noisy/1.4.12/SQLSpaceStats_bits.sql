select * from SQLSpaceStats a
    join
        (
        select Server_Name, dbname, flag, FileID, FileGroup, total_space, usedspace, freespace, freepct, Name, [FileName], LastUpdate, max(seq_num) AS max_seq_num
            from SQLSpaceStats
                                                           group by Server_Name, dbname, flag, FileID, FileGroup, total_space, usedspace, freespace, freepct, Name, LastUpdate, [FileName]having count(*) > 1
        ) b
    on a.Server_Name = b.Server_Name
       and a.dbname = b.dbname and a.flag = b.flag
       and a.FileID = b.FileID
       and a.FileGroup = b.FileGroup
       AND a.total_space = b.total_space
       AND a.usedspace = b.usedspace
       AND a.freespace = b.freespace
       AND a.freepct = b.freepct
       AND a.Name = b.Name
       AND a.[FileName] = b.[FileName]
        AND a.LastUpdate = b.LastUpdate
    AND a.seq_num < b.max_seq_num

----------------------------------------------------------

SELECT server_name, ServerID, dbname, LastUpdate, flag, Fileid, FileGroup, total_space, freespace, freepct, [FileName]
from SQLSpaceStats
--where server_name IN ('HARTFORD','STGSQL610','STGSQL611','STGSQLCBS620','STGSQLDOC710','STGSQLMET620') --= '9999'
--where server_name = 'STGSQLDOC710'
--WHERE LastUpdate > GetDate() - 2
--AND server_name = 'STGSQL611'
GROUP BY LastUpdate
                  , dbname
                            , server_name
                            , ServerID
                            , flag
                            , Fileid
                            , FileGroup
                            , total_space
                            , freespace
                            , freepct
                            , [FileName]
order by dbname, LastUpdate desc
-----------------------------------------------------------

select * from SQLSpaceStats
where DATEPART(day, lastupdate) = DATEPART(day, GetDate())
AND DATEPART(month, LastUpdate) = DATEPART(month, GETDATE())
AND DATEPART(year, LastUpdate) = DATEPART(year, GETDATE())
order by dbname

----------------------------------------------------------



delete from SQLSpaceStats where ServerID = '9999'
----------------------------------------------------------

delete FROM SQLSpaceStats WHERE lastUpdate = '2011-07-13 10:19:33.363'