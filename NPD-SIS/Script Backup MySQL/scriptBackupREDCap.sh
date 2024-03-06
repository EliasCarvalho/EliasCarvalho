#!/bin/bash
DATA=`date +%Y%m%d%H%M`
mysqldump --force --opt -uroot -p123Mudar redcap  > backupsRealizados/REDCap_"$DATA".sql 
