# 首先安装 依赖 
yum -y install gcc pcre-devel zlib-devel openssl openssl-devel

# 下载nginx
wget https://nginx.org/download/nginx-1.19.2.tar.gz

# 解压
tar -zxvf nginx-1.19.2.tar.gz

# 配置
./configure --prefix=/reisen/nginx

# 测试
./sbin/nginx -t

# 运行
cd /sbin ./nginx