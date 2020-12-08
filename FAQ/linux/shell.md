找到以*.jar结尾的文件 复制到*.jar.bak文件
for var in *.jar; do mv "$var" "${var%}.bak"; done

找到xxx.jar的进程，并且得到进程id 然后kill -9干掉
ps -ef | grep xxx.jar | grep -v grep | cut -c 9-15 | xargs kill -s 9