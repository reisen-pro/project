## Linux下开启FTP服务

查看是否已经安装FTP服务

`which vsftpd`



如果看到有vsftpd的目录说明服务器已经安装了ftp软件

`sudo yum install vsftpd`



如提示 -bash: netstat: 未找到命令 

`yum -y install net-tools`



如果连接失败，清空防火墙

`iptables -F`