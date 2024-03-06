#!/bin/bash
DATA=`date +%Y%m%d%H%M`
mysqldump --force --opt -ucehuAdmin -pCehu@2021 cehu  > backupsRealizados/cehu_"$DATA".sql 
